package edu.dosw.lab.bankify.repository;

import edu.dosw.lab.bankify.domain.Client;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repositorio en memoria para clientes
 */
public class ClientRepository {
    private final Map<String, Client> clients = new ConcurrentHashMap<>();
    
    public void save(Client client) {
        clients.put(client.getId(), client);
    }
    
    public Optional<Client> findById(String id) {
        return Optional.ofNullable(clients.get(id));
    }
    
    public boolean existsById(String id) {
        return clients.containsKey(id);
    }
}