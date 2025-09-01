package edu.dosw.lab.planningpoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamMemberFactory {
    // Matriz con información de los miembros del equipo [nombre, rol]
    private static final String[][] MIEMBROS_DATOS = {
        {"Juana", "Desarrolladora"},
        {"Carlos", "Tester"},
        {"Christian", "Analista"}
    };
    
    public static List<TeamMember> createTeamMembers(Scanner scanner) {
        List<TeamMember> miembros = new ArrayList<>();
        for (String[] datos : MIEMBROS_DATOS) {
            miembros.add(new TeamMember(datos[0], datos[1], scanner));
        }
        return miembros;
    }
    
    // Clase interna TeamMember
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