package edu.dosw.lab.planningpoker;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para ejecutar la aplicación de Planning Poker.
 * Contiene toda la lógica de inicialización y ejecución.
 */
public class PlanningPokerMain {
    
    /**
     * Método que inicia la sesión de Planning Poker completa
     */
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido al sistema de Planning Poker para Bankify");
        System.out.println("---------------------------------------------------");
        
        // Obtener historias de usuario usando la factory
        List<UserStory> historias = obtenerHistoriasUsuario();
        
        // Crear y ejecutar sesión de Planning Poker
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        sesion.iniciarSesion();
        
        // Cerrar recursos
        scanner.close();
        System.out.println("\nSesión finalizada. ¡Gracias por usar Planning Poker!");
    }
    
    /**
     * Obtiene las historias de usuario para la sesión
     * @return Lista de historias de usuario
     */
    private static List<UserStory> obtenerHistoriasUsuario() {
        // Obtener historias predefinidas de Bankify
        List<UserStory> historias = UserStoryFactory.createBankifyUserStories();
        
        // Agregar historia personalizada adicional
        historias.add(UserStoryFactory.createUserStory(
            "HU06", 
            "cliente", 
            "transferir dinero entre cuentas", 
            "poder mover fondos fácilmente", 
            "Funcionalidad"
        ));
        
        return historias;
    }
    
    /**
     * Punto de entrada alternativo si se quiere ejecutar directamente esta clase
     */
    public static void main(String[] args) {
        ejecutar();
    }
}