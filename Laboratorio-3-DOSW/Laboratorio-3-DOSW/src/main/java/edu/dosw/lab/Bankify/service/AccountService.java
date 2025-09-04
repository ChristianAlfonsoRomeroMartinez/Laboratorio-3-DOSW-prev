package edu.dosw.lab.bankify.service;

import edu.dosw.lab.bankify.domain.Account;
import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.repository.AccountRepository;


/**
 * Servicio de gestión de cuentas: crear, consultar saldo, depositar.
 */
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountValidator validator;

    public AccountService(AccountRepository accountRepository, AccountValidator validator) {
        this.accountRepository = accountRepository;
        this.validator = validator;
    }

    /**
     * Crea una cuenta y la asocia al cliente si el número es válido.
     * @throws IllegalArgumentException si el número no es válido o si ya existe.
     */
    public Account createAccount(Client client, String accountNumber) {
        if (!validator.isValid(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number");
        }
        if (accountRepository.findByNumber(accountNumber).isPresent()) {
            throw new IllegalArgumentException("Account already exists");
        }
        Account account = new Account(accountNumber);
        accountRepository.save(account);
        if (client != null) client.addAccount(account);
        return account;
    }

    /**
     * Realiza un depósito en la cuenta especificada.
     * @throws IllegalArgumentException si el monto es negativo o la cuenta no existe.
     */
    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance() + amount);
    }

    /**
     * Consulta el saldo de una cuenta.
     * @throws IllegalArgumentException si la cuenta no existe.
     */
    public double getBalance(String accountNumber) {
        return accountRepository.findByNumber(accountNumber)
                .map(Account::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
