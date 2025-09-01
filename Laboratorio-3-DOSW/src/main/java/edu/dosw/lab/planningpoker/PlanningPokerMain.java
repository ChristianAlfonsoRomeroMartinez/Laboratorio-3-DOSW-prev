package edu.dosw.lab.planningpoker;

import java.util.List;
import java.util.Scanner;

/**
 * Esta es la clase principal del sistema de Planning Poker.
 * Aquí se inicia todo el proceso de estimación de historias de usuario.
 * Es como el "jefe" que organiza la reunión y hace que todo funcione.
 */
public class PlanningPokerMain {
    
    /**
     * Este método arranca toda la sesión de Planning Poker.
     * Prepara las historias, crea la sesión y la ejecuta.
     * Al final cierra todo ordenadamente.
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
     * Este método junta todas las historias de usuario que vamos a estimar.
     * Toma las historias predefinidas de Bankify y agrega una extra.
     * @return Una lista con todas las historias listas para votar
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
     * Este es un método extra por si alguien quiere ejecutar esta clase directamente.
     * Básicamente llama al método ejecutar() que hace todo el trabajo.
     */
    public static void main(String[] args) {
        ejecutar();
    }
}