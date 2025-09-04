package edu.dosw.lab.bankify.repository;

import edu.dosw.lab.bankify.domain.Account;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repositorio en memoria de cuentas.
 */
public class AccountRepository {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public void save(Account account) {
        accounts.put(account.getNumber(), account);
    }

    public Optional<Account> findByNumber(String number) {
        return Optional.ofNullable(accounts.get(number));
    }
}
