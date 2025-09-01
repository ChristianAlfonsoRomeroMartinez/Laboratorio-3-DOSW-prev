package edu.dosw.lab.bankify;

import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.domain.Bank;
import edu.dosw.lab.bankify.repository.AccountRepository;
import edu.dosw.lab.bankify.repository.BankRepository;
import edu.dosw.lab.bankify.service.AccountService;
import edu.dosw.lab.bankify.service.AccountValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    private AccountService service;

    @BeforeEach
    void setup() {
        BankRepository bankRepository = new BankRepository();
        bankRepository.save(new Bank("01", "BANCOLOMBIA"));
        bankRepository.save(new Bank("02", "DAVIVIENDA"));

        AccountRepository accountRepository = new AccountRepository();
        AccountValidator validator = new AccountValidator(bankRepository);
        service = new AccountService(accountRepository, validator);
    }

    @Test
    void createAccountAndDepositAndBalance() {
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        var account = service.createAccount(client, "0112345678");
        assertNotNull(account);
        assertEquals(1, client.getAccounts().size());

        assertEquals(0.0, service.getBalance("0112345678"));
        service.deposit("0112345678", 200.0);
        assertEquals(200.0, service.getBalance("0112345678"));
    }

    @Test
    void cannotCreateInvalidAccount() {
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(client, "991234"));
    }

    @Test
    void depositMustBePositive() {
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        service.createAccount(client, "0212345678");
        assertThrows(IllegalArgumentException.class, () -> service.deposit("0212345678", -10));
    }

    @Test
    void accountMustExist() {
        assertThrows(IllegalArgumentException.class, () -> service.getBalance("0111111111"));
        assertThrows(IllegalArgumentException.class, () -> service.deposit("0111111111", 10));
    }
}
