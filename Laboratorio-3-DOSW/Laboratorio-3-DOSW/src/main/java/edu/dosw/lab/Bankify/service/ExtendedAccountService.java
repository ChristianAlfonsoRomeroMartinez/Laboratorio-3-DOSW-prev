package edu.dosw.lab.bankify.service;

import edu.dosw.lab.bankify.domain.Account;
import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.exception.BankingBusinessException;
import edu.dosw.lab.bankify.repository.AccountRepository;
import edu.dosw.lab.bankify.repository.ClientRepository;

/**
 * Servicio extendido que implementa todas las reglas de negocio adicionales
 */
public class ExtendedAccountService {
    
    private final AccountService accountService;
    private final AccountValidator accountValidator;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    
    public ExtendedAccountService(AccountService accountService, 
                                 AccountValidator accountValidator,
                                 ClientRepository clientRepository,
                                 AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }
    
    /**
     * Crea una cuenta validando todas las reglas de negocio extendidas
     */
    public Account createAccountWithBusinessRules(String clientId, String accountNumber) {
        // Validar que el cliente existe
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new BankingBusinessException("Cliente no registrado en el sistema"));
        
        // Validar formato de cuenta (10 dígitos exactos)
        validateAccountFormat(accountNumber);
        
        // Validar que el banco existe
        validateBankExists(accountNumber);
        
        // Validar que la cuenta no existe
        validateAccountNotExists(accountNumber);
        
        // Validar que el cliente no tiene ya esta cuenta
        validateClientAccountAssociation(client, accountNumber);
        
        // Crear la cuenta usando el servicio existente
        return accountService.createAccount(client, accountNumber);
    }
    
    /**
     * Realiza depósito con validaciones extendidas
     */
    public void depositWithValidation(String accountNumber, double amount) {
        // Validar que la cuenta existe y es válida
        validateAccountExistsAndValid(accountNumber);
        
        accountService.deposit(accountNumber, amount);
    }
    
    /**
     * Consulta saldo con validaciones extendidas
     */
    public double getBalanceWithValidation(String accountNumber) {
        // Validar que la cuenta existe
        validateAccountExists(accountNumber);
        
        return accountService.getBalance(accountNumber);
    }
    
    private void validateAccountFormat(String accountNumber) {
        if (accountNumber == null || accountNumber.length() != 10) {
            throw new BankingBusinessException("El número de cuenta debe tener exactamente 10 dígitos");
        }
        
        if (!accountNumber.matches("\\d+")) {
            throw new BankingBusinessException("El número de cuenta solo puede contener números");
        }
    }
    
    private void validateBankExists(String accountNumber) {
        String bankCode = accountNumber.substring(0, 2);
        if (!accountValidator.isValid(accountNumber)) {
            throw new BankingBusinessException("El código de banco " + bankCode + " no está registrado en el sistema");
        }
    }
    
    private void validateAccountNotExists(String accountNumber) {
        if (accountRepository.findByNumber(accountNumber).isPresent()) {
            throw new BankingBusinessException("Ya existe una cuenta con el número: " + accountNumber);
        }
    }
    
    private void validateClientAccountAssociation(Client client, String accountNumber) {
        boolean accountAlreadyAssociated = client.getAccounts().stream()
                .anyMatch(acc -> acc.getNumber().equals(accountNumber));
        
        if (accountAlreadyAssociated) {
            throw new BankingBusinessException("La cuenta ya está asociada a este cliente");
        }
    }
    
    private void validateAccountExistsAndValid(String accountNumber) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new BankingBusinessException("Cuenta no encontrada: " + accountNumber));
        
        if (!"ACTIVA".equals(account.getStatus())) {
            throw new BankingBusinessException("La cuenta no está activa: " + accountNumber);
        }
    }
    
    private void validateAccountExists(String accountNumber) {
        accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new BankingBusinessException("Cuenta no encontrada: " + accountNumber));
    }
}