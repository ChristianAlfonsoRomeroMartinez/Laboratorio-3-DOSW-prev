package edu.dosw.lab.bankify.domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa un cliente de Bankify.
 */
public class Client {
    private String id;
    private String name;
    private String type;
    private String email;
    private final List<Account> accounts = new ArrayList<>();

    public Client(String id, String name, String type, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    /**
     * @return lista inmutable de cuentas del cliente.
     */
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    /** Agrega una cuenta a la lista interna. */
    public void addAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("account cannot be null");
        this.accounts.add(account);
    }
}
