package edu.dosw.lab.bankify.repository;

import edu.dosw.lab.bankify.domain.Bank;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Repositorio en memoria para bancos.
 * Cumple SRP y permite intercambiar por otra implementación (DIP).
 */
public class BankRepository {
    private final Map<String, Bank> banks = new ConcurrentHashMap<>();

    public BankRepository() { }

    public void save(Bank bank) {
        banks.put(bank.getCode(), bank);
    }

    public Optional<Bank> findByCode(String code) {
        return Optional.ofNullable(banks.get(code));
    }

    public List<Bank> findAll() {
        return banks.values().stream().sorted(Comparator.comparing(Bank::getCode)).collect(Collectors.toList());
    }

    public boolean existsCode(String code) {
        return banks.containsKey(code);
    }
}
