package edu.dosw.lab.planningpoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase es una fábrica para crear miembros del equipo.
 * Es como una máquina que produce objetos TeamMember con la información necesaria.
 * Usa el patrón Factory para crear los miembros de manera organizada.
 */
public class TeamMemberFactory {
    // Matriz con información de los miembros del equipo [nombre, rol]
    private static final String[][] MIEMBROS_DATOS = {
        {"Juana", "Desarrolladora"},
        {"Carlos", "Tester"},
        {"Christian", "Analista"}
    };
    
    /**
     * Este método crea una lista de miembros del equipo usando los datos predefinidos.
     * Toma la información de la matriz MIEMBROS_DATOS y crea objetos TeamMember.
     * @param scanner El scanner para que los miembros puedan votar
     * @return Una lista con todos los miembros del equipo
     */
    public static List<TeamMember> createTeamMembers(Scanner scanner) {
        List<TeamMember> miembros = new ArrayList<>();
        for (String[] datos : MIEMBROS_DATOS) {
            miembros.add(new TeamMember(datos[0], datos[1], scanner));
        }
        return miembros;
    }
    
    // Clase interna TeamMember
    /**
     * Esta clase representa a un miembro del equipo en la sesión de Planning Poker.
     * Cada miembro tiene nombre, rol y puede votar en las historias.
     * Es como un participante en la reunión con sus propias opiniones.
     */
    public static class TeamMember {
        private String nombre;
        private String rol;
        private Scanner scanner;
        
        public TeamMember(String nombre, String rol, Scanner scanner) {
            this.nombre = nombre;
            this.rol = rol;
            this.scanner = scanner;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public String getRol() {
            return rol;
        }
        
        /**
         * Este método hace que el miembro vote en una historia.
         * Pide el voto por consola, valida que sea un número de Fibonacci válido,
         * y lo devuelve. Si no es válido, pide de nuevo.
         * @param historia La historia en la que se está votando
         * @return El voto válido (1, 2, 3, 5, 8, 13)
         */
        public int votar(UserStory historia) {
            int voto = 0;
            boolean votoValido = false;
            
            while (!votoValido) {
                System.out.printf("%s (%s), ingresa tu voto (1,2,3,5,8,13): ", nombre, rol);
                try {
                    voto = scanner.nextInt();
                    votoValido = esVotoValido(voto);
                    if (!votoValido) {
                        System.out.println("Valor no válido. Use: 1, 2, 3, 5, 8, 13");
                    }
                } catch (Exception e) {
                    System.out.println("Por favor ingresa un número válido");
                    scanner.next(); // limpiar buffer
                }
            }
            
            return voto;
        }
        
        private boolean esVotoValido(int voto) {
            int[] fibonacci = {1, 2, 3, 5, 8, 13};
            for (int fib : fibonacci) {
                if (voto == fib) {
                    return true;
                }
            }
            return false;
        }
    }
}