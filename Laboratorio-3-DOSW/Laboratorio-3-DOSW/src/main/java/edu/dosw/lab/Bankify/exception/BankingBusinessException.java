package edu.dosw.lab.bankify.exception;

/**
 * Excepción personalizada para errores de negocio en el sistema bancario
 */
public class BankingBusinessException extends RuntimeException {
    public BankingBusinessException(String message) {
        super(message);
    }
    
    public BankingBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}