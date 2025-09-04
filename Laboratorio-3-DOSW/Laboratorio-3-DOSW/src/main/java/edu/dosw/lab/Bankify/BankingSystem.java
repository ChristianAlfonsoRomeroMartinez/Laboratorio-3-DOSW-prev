package edu.dosw.lab.bankify;

import edu.dosw.lab.bankify.domain.Bank;
import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.repository.BankRepository;
import edu.dosw.lab.bankify.repository.AccountRepository;
import edu.dosw.lab.bankify.repository.ClientRepository;
import edu.dosw.lab.bankify.service.AccountService;
import edu.dosw.lab.bankify.service.AccountValidator;
import edu.dosw.lab.bankify.service.ExtendedAccountService;

/**
 * Demo del sistema extendido con todas las reglas de negocio
 */
public class BankingSystem {
    
    public static void main(String[] args) {
        // Configuración inicial
        BankRepository bankRepository = new BankRepository();
        bankRepository.save(new Bank("01", "BANCOLOMBIA"));
        bankRepository.save(new Bank("02", "DAVIVIENDA"));
        bankRepository.save(new Bank("03", "BANCO DE BOGOTÁ"));
        
        AccountRepository accountRepository = new AccountRepository();
        ClientRepository clientRepository = new ClientRepository();
        
        AccountValidator validator = new AccountValidator(bankRepository);
        AccountService basicService = new AccountService(accountRepository, validator);
        ExtendedAccountService extendedService = new ExtendedAccountService(
            basicService, validator, clientRepository, accountRepository);
        
        // Registrar clientes
        Client client1 = new Client("C001", "Juan Pérez", "NATURAL", "juan@email.com");
        Client client2 = new Client("C002", "Empresa XYZ", "JURIDICA", "empresa@xyz.com");
        clientRepository.save(client1);
        clientRepository.save(client2);
        
        try {
            // Crear cuentas con validaciones extendidas
            extendedService.createAccountWithBusinessRules("C001", "0112345678");
            extendedService.createAccountWithBusinessRules("C002", "0298765432");
            
            System.out.println("Cuentas creadas exitosamente");
            
            // Operaciones válidas
            extendedService.depositWithValidation("0112345678", 500.0);
            double balance = extendedService.getBalanceWithValidation("0112345678");
            System.out.println("Saldo cuenta 0112345678: " + balance);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Intentar operaciones inválidas
        try {
            extendedService.createAccountWithBusinessRules("C001", "9912345678"); // Banco no existe
        } catch (Exception e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
    }
}