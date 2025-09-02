package edu.dosw.lab;

import edu.dosw.lab.bankify.domain.Bank;
import edu.dosw.lab.bankify.domain.Client;
import edu.dosw.lab.bankify.repository.AccountRepository;
import edu.dosw.lab.bankify.repository.BankRepository;
import edu.dosw.lab.bankify.service.AccountService;
import edu.dosw.lab.bankify.service.AccountValidator;
import edu.dosw.lab.planningpoker.PlanningPokerMain;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        PlanningPokerMain.ejecutar();
    
     // Crear repositorio y validador
        BankRepository bankRepository = new BankRepository();
        bankRepository.save(new Bank("01", "BANCOLOMBIA"));
        AccountRepository accountRepository = new AccountRepository();
        AccountValidator validator = new AccountValidator(bankRepository);

        // Crear servicio con dependencias inyectadas
        AccountService service = new AccountService(accountRepository, validator);

        // Crear cliente
        Client client = new Client("C1", "Ana", "NATURAL", "ana@test.com");

        // Crear cuenta y hacer operaciones
        service.createAccount(client, "0112345678");
        service.deposit("0112345678", 200.0);

        // Mostrar resultado
        System.out.println("Cliente: " + client.getName());
        System.out.println("Saldo de la cuenta 0112345678: " + service.getBalance("0112345678"));
    }

}
