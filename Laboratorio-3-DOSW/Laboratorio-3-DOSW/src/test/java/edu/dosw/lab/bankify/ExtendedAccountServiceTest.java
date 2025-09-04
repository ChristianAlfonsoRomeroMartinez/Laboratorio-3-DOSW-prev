package edu.dosw.lab.bankify;

import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.domain.Bank;
import edu.dosw.lab.bankify.domain.Account;
import edu.dosw.lab.bankify.repository.AccountRepository;
import edu.dosw.lab.bankify.repository.BankRepository;
import edu.dosw.lab.bankify.repository.ClientRepository;
import edu.dosw.lab.bankify.service.AccountService;
import edu.dosw.lab.bankify.service.AccountValidator;
import edu.dosw.lab.bankify.service.ExtendedAccountService;
import edu.dosw.lab.bankify.exception.BankingBusinessException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExtendedAccountServiceTest {

    private ExtendedAccountService extendedService;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        BankRepository bankRepository = new BankRepository();
        bankRepository.save(new Bank("01", "BANCOLOMBIA"));
        bankRepository.save(new Bank("02", "DAVIVIENDA"));

        accountRepository = new AccountRepository();
        clientRepository = new ClientRepository();
        
        AccountValidator validator = new AccountValidator(bankRepository);
        AccountService basicService = new AccountService(accountRepository, validator);
        extendedService = new ExtendedAccountService(basicService, validator, clientRepository, accountRepository);
    }

    @Test
    void shouldCreateAccountWhenAllBusinessRulesAreMet() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String validAccountNumber = "0112345678";

        // When
        Account account = extendedService.createAccountWithBusinessRules("C1", validAccountNumber);

        // Then
        assertNotNull(account);
        assertEquals(validAccountNumber, account.getNumber());
        assertEquals(1, client.getAccounts().size());
    }

    @Test
    void shouldNotCreateAccountWhenNumberHasInvalidLength() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String invalidAccountNumber = "011234567"; // 9 dígitos

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.createAccountWithBusinessRules("C1", invalidAccountNumber));
    }

    @Test
    void shouldNotCreateAccountWhenNumberContainsNonDigitCharacters() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String invalidAccountNumber = "01ABC45678"; // contiene letras

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.createAccountWithBusinessRules("C1", invalidAccountNumber));
    }

    @Test
    void shouldNotCreateAccountWhenBankCodeIsNotRegistered() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String invalidAccountNumber = "9912345678"; // banco 99 no registrado

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.createAccountWithBusinessRules("C1", invalidAccountNumber));
    }

    @Test
    void shouldNotCreateAccountWhenClientIsNotRegistered() {
        // Given
        String unregisteredClientId = "UNKNOWN";
        String validAccountNumber = "0112345678";

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.createAccountWithBusinessRules(unregisteredClientId, validAccountNumber));
    }

    @Test
    void shouldNotCreateAccountWhenAccountNumberAlreadyExists() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber = "0112345678";
        
        // Crear cuenta por primera vez
        extendedService.createAccountWithBusinessRules("C1", accountNumber);

        // When & Then - Intentar crear cuenta duplicada
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.createAccountWithBusinessRules("C1", accountNumber));
    }

    @Test
    void shouldNotAllowDuplicateAccountAssociationToSameClient() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber = "0112345678";
        
        // Crear cuenta exitosamente
        extendedService.createAccountWithBusinessRules("C1", accountNumber);

        // When & Then - Intentar crear la misma cuenta otra vez
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.createAccountWithBusinessRules("C1", accountNumber));
    }

    @Test
    void shouldAllowDepositToValidActiveAccount() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber = "0212345678";
        double depositAmount = 100.0;
        
        extendedService.createAccountWithBusinessRules("C1", accountNumber);

        // When
        extendedService.depositWithValidation(accountNumber, depositAmount);
        double balance = extendedService.getBalanceWithValidation(accountNumber);

        // Then
        assertEquals(depositAmount, balance);
    }

    @Test
    void shouldNotAllowDepositToNonExistentAccount() {
        // Given
        String nonExistentAccountNumber = "9999999999";
        double depositAmount = 100.0;

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.depositWithValidation(nonExistentAccountNumber, depositAmount));
    }

    @Test
    void shouldNotAllowBalanceQueryForNonExistentAccount() {
        // Given
        String nonExistentAccountNumber = "9999999999";

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.getBalanceWithValidation(nonExistentAccountNumber));
    }

    @Test
    void shouldNotAllowOperationsOnInactiveAccount() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber = "0212345678";
        
        Account account = extendedService.createAccountWithBusinessRules("C1", accountNumber);
        account.setStatus("INACTIVA");
        double depositAmount = 100.0;

        // When & Then
        assertThrows(BankingBusinessException.class, 
            () -> extendedService.depositWithValidation(accountNumber, depositAmount));
    }

    @Test
    void shouldAllowOperationsOnActiveAccount() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber = "0212345678";
        double depositAmount = 150.0;
        
        Account account = extendedService.createAccountWithBusinessRules("C1", accountNumber);
        account.setStatus("ACTIVA"); // Asegurar estado activo

        // When
        extendedService.depositWithValidation(accountNumber, depositAmount);
        double balance = extendedService.getBalanceWithValidation(accountNumber);

        // Then
        assertEquals(depositAmount, balance);
    }

    @Test
    void shouldMaintainAccountStatusAfterCreation() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber = "0112345678";

        // When
        Account account = extendedService.createAccountWithBusinessRules("C1", accountNumber);

        // Then
        assertEquals("ACTIVA", account.getStatus());
        assertEquals(accountNumber, account.getNumber());
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void shouldAllowMultipleAccountsForSameClientWithDifferentNumbers() {
        // Given
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        clientRepository.save(client);
        String accountNumber1 = "0112345678";
        String accountNumber2 = "0212345678";

        // When
        Account account1 = extendedService.createAccountWithBusinessRules("C1", accountNumber1);
        Account account2 = extendedService.createAccountWithBusinessRules("C1", accountNumber2);

        // Then
        assertNotNull(account1);
        assertNotNull(account2);
        assertEquals(2, client.getAccounts().size());
        assertNotEquals(account1.getNumber(), account2.getNumber());
    }

    @Test
    void shouldAllowMultipleClientsWithSameBankButDifferentAccountNumbers() {
        // Given
        Client client1 = new Client("C1", "Ana", "NATURAL", "ana@test.com");
        Client client2 = new Client("C2", "Juan", "NATURAL", "juan@test.com");
        clientRepository.save(client1);
        clientRepository.save(client2);
        
        String accountNumber1 = "0112345678";
        String accountNumber2 = "0112345679"; // Mismo banco, diferente número

        // When
        Account account1 = extendedService.createAccountWithBusinessRules("C1", accountNumber1);
        Account account2 = extendedService.createAccountWithBusinessRules("C2", accountNumber2);

        // Then
        assertNotNull(account1);
        assertNotNull(account2);
        assertEquals(1, client1.getAccounts().size());
        assertEquals(1, client2.getAccounts().size());
        assertNotEquals(account1.getNumber(), account2.getNumber());
    }
}