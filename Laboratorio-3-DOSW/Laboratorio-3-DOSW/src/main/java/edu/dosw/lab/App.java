package edu.dosw.lab;

import edu.dosw.lab.bankify.domain.Bank;
import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.repository.AccountRepository;
import edu.dosw.lab.bankify.repository.BankRepository;
import edu.dosw.lab.bankify.repository.ClientRepository;
import edu.dosw.lab.bankify.service.AccountService;
import edu.dosw.lab.bankify.service.AccountValidator;
import edu.dosw.lab.bankify.service.ExtendedAccountService;
import edu.dosw.lab.planningPoker.PlanningPokerMain;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "=== SISTEMA BANCARIO EXTENDIDO ===" );
        
        // Ejecutar Planning Poker (existente)
        PlanningPokerMain.ejecutar();
        
        // Separador
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DEMOSTRACIÓN DEL SISTEMA BANCARIO EXTENDIDO");
        System.out.println("=".repeat(50));
    
        // Configuración del sistema bancario extendido
        demoBankingSystemExtended();
    }
    
    private static void demoBankingSystemExtended() {
        try {
            // 1. Configurar repositorios
            BankRepository bankRepository = new BankRepository();
            bankRepository.save(new Bank("01", "BANCOLOMBIA"));
            bankRepository.save(new Bank("02", "DAVIVIENDA"));
            bankRepository.save(new Bank("03", "BANCO DE BOGOTÁ"));
            
            AccountRepository accountRepository = new AccountRepository();
            ClientRepository clientRepository = new ClientRepository();
            
            // 2. Crear servicios
            AccountValidator validator = new AccountValidator(bankRepository);
            AccountService basicService = new AccountService(accountRepository, validator);
            ExtendedAccountService extendedService = new ExtendedAccountService(
                basicService, validator, clientRepository, accountRepository);

            // 3. Registrar clientes
            Client client1 = new Client("C001", "Ana García", "NATURAL", "ana@test.com");
            Client client2 = new Client("C002", "Empresa XYZ", "JURIDICA", "empresa@xyz.com");
            clientRepository.save(client1);
            clientRepository.save(client2);

            System.out.println("Clientes registrados exitosamente:");
            System.out.println("- " + client1.getName() + " (ID: " + client1.getId() + ")");
            System.out.println("- " + client2.getName() + " (ID: " + client2.getId() + ")");
            System.out.println();

            // 4. Crear cuentas con validaciones extendidas
            System.out.println("Creando cuentas bancarias...");
            
            // Cuenta válida
            extendedService.createAccountWithBusinessRules("C001", "0112345678");
            System.out.println("✓ Cuenta 0112345678 creada para Ana García");
            
            // Otra cuenta válida
            extendedService.createAccountWithBusinessRules("C002", "0298765432");
            System.out.println("✓ Cuenta 0298765432 creada para Empresa XYZ");
            
            System.out.println();

            // 5. Realizar operaciones
            System.out.println("Realizando operaciones bancarias...");
            
            // Depósito exitoso
            extendedService.depositWithValidation("0112345678", 500.0);
            System.out.println("✓ Depósito de $500 en cuenta 0112345678");
            
            // Consulta de saldo
            double balance = extendedService.getBalanceWithValidation("0112345678");
            System.out.println("✓ Saldo cuenta 0112345678: $" + balance);
            
            // Más operaciones
            extendedService.depositWithValidation("0298765432", 1000.0);
            System.out.println("✓ Depósito de $1000 en cuenta 0298765432");
            
            double balance2 = extendedService.getBalanceWithValidation("0298765432");
            System.out.println("✓ Saldo cuenta 0298765432: $" + balance2);
            
            System.out.println();

            // 6. Demostrar validaciones de reglas de negocio
            System.out.println("Probando validaciones de reglas de negocio...");
            
            // Intentar crear cuenta con banco no registrado
            try {
                extendedService.createAccountWithBusinessRules("C001", "9912345678");
            } catch (Exception e) {
                System.out.println("✗ Validación exitosa: " + e.getMessage());
            }
            
            // Intentar crear cuenta con formato inválido
            try {
                extendedService.createAccountWithBusinessRules("C001", "01ABC56789");
            } catch (Exception e) {
                System.out.println("✗ Validación exitosa: " + e.getMessage());
            }
            
            // Intentar crear cuenta duplicada
            try {
                extendedService.createAccountWithBusinessRules("C001", "0112345678");
            } catch (Exception e) {
                System.out.println("✗ Validación exitosa: " + e.getMessage());
            }
            
            // Intentar operar con cuenta inexistente
            try {
                extendedService.getBalanceWithValidation("9999999999");
            } catch (Exception e) {
                System.out.println("✗ Validación exitosa: " + e.getMessage());
            }

            System.out.println("\n" + "=".repeat(50));
            System.out.println("DEMOSTRACIÓN COMPLETADA EXITOSAMENTE");
            System.out.println("=".repeat(50));

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}