package edu.dosw.lab.bankify.service;

import edu.dosw.lab.bankify.repository.BankRepository;

/**
 * Valida números de cuenta según reglas del negocio.
 * - 10 dígitos exactos
 * - Solo números
 * - Los dos primeros dígitos corresponden a un banco registrado
 */
public class AccountValidator {

    private final BankRepository bankRepository;

    public AccountValidator(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    /**
     * Valida un número de cuenta según las reglas.
     * @param accountNumber número de 10 dígitos
     * @return true si es válido, false en otro caso
     */
    public boolean isValid(String accountNumber) {
        if (accountNumber == null) return false;
        if (accountNumber.length() != 10) return false;
        if (!accountNumber.chars().allMatch(Character::isDigit)) return false;
        String bankCode = accountNumber.substring(0, 2);
        return bankRepository.existsCode(bankCode);
    }
}
