package edu.dosw.lab.bankify.domain;
/**
 * Representa una cuenta bancaria.
 */
public class Account {
    private final String number; // 10 dígitos
    private double balance;
    private String status;

    public Account(String number) {
        this(number, 0.0, "ACTIVA");
    }

    public Account(String number, double balance, String status) {
        this.number = number;
        this.balance = balance;
        this.status = status;
    }

    public String getNumber() { return number; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
