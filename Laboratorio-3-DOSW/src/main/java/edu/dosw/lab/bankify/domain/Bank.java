package edu.dosw.lab.bankify.domain;

/**
 * Representa un Banco registrado en el sistema.
 */
public class Bank {
    private final String code; // 2 dígitos
    private String name;

    public Bank(String code, String name) {
        if (code == null || !code.matches("\\d{2}")) {
            throw new IllegalArgumentException("Bank code must be exactly 2 digits");
        }
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
