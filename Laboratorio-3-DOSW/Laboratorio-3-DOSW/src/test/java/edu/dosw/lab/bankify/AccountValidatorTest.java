package edu.dosw.lab.bankify;

import edu.dosw.lab.bankify.domain.Bank;
import edu.dosw.lab.bankify.repository.BankRepository;
import edu.dosw.lab.bankify.service.AccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountValidatorTest {

    private AccountValidator validator;
    private BankRepository bankRepository;

    @BeforeEach
    void setup() {
        bankRepository = new BankRepository();
        bankRepository.save(new Bank("01", "BANCOLOMBIA"));
        bankRepository.save(new Bank("02", "DAVIVIENDA"));
        validator = new AccountValidator(bankRepository);
    }

    @Test
    void validAccountNumber() {
        assertTrue(validator.isValid("0112345678"));
    }

    @Test
    void invalidLength() {
        assertFalse(validator.isValid("011234567"));
        assertFalse(validator.isValid("01123456789"));
    }

    @Test
    void nonDigitsNotAllowed() {
        assertFalse(validator.isValid("01ABC45678"));
        assertFalse(validator.isValid("01-3456789"));
    }

    @Test
    void bankCodeMustExist() {
        assertFalse(validator.isValid("9912345678")); // 99 no registrado
    }

    @Test
    void nullIsInvalid() {
        assertFalse(validator.isValid(null));
    }
}
