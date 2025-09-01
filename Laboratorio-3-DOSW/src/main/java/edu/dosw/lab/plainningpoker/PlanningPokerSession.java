package edu.dosw.lab.planningpoker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.dosw.lab.planningpoker.TeamMemberFactory.TeamMember;

/**
 * Esta clase maneja toda la sesión de Planning Poker.
 * Es como el moderador de la reunión: organiza las votaciones,
 * verifica si hay consenso y muestra los resultados.
 * Usa un patrón simple de sesión para mantener todo ordenado.
 */
public class PlanningPokerSession {
    private List<UserStory> historias;
    private List<TeamMember> miembros;
    private Scanner scanner;
    
    /**
     * Constructor que prepara la sesión con las historias y el scanner.
     * También crea los miembros del equipo usando la fábrica.
     * @param historias Lista de historias a estimar
     * @param scanner Para leer las entradas del usuario
     */
    public PlanningPokerSession(List<UserStory> historias, Scanner scanner) {
        this.historias = historias;
        this.scanner = scanner;
        this.miembros = TeamMemberFactory.createTeamMembers(scanner);
    }
    
    /**
     * Este método inicia la sesión completa.
     * Muestra las reglas, estima cada historia y al final muestra el resumen.
     */
    public void iniciarSesion() {
        System.out.println("\n=== SESIÓN DE PLANNING POKER ===");
        System.out.println("Valores válidos: 1, 2, 3, 5, 8, 13");
        System.out.println("===============================\n");
        
        for (UserStory historia : historias) {
            estimarHistoria(historia);
        }
        
        mostrarResumen();
    }
    
    private void estimarHistoria(UserStory historia) {
        boolean consensoAlcanzado = false;
        Map<String, Integer> votos = new HashMap<>();
        
        System.out.println("\n" + historia);
        
        while (!consensoAlcanzado) {
            votos.clear();
            
            // Recoger votos
            for (TeamMember miembro : miembros) {
                int voto = miembro.votar(historia);
                votos.put(miembro.getNombre(), voto);
            }
            
            // Verificar consenso
            if (hayConsenso(votos)) {
                // Todos votaron igual
                int estimacionFinal = votos.values().iterator().next();
                historia.setEstimacionFinal(estimacionFinal);
                System.out.println("¡Consenso alcanzado! Puntuación final: " + estimacionFinal);
                consensoAlcanzado = true;
            } else {
                // Mostrar votos y repetir
                System.out.println("Votos actuales:");
                for (Map.Entry<String, Integer> entry : votos.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("Votos divergentes – Discutan y vuelvan a votar");
            }
        }
    }
    
    private boolean hayConsenso(Map<String, Integer> votos) {
        if (votos.isEmpty()) {
            return false;
        }
        
        Set<Integer> votosUnicos = new HashSet<>(votos.values());
        return votosUnicos.size() == 1; // Si solo hay un valor único, todos votaron igual
    }
    
    private void mostrarResumen() {
        System.out.println("\n=== RESUMEN DE ESTIMACIONES ===");
        for (UserStory historia : historias) {
            System.out.println("- " + historia.getId() + ": " + historia.getEstimacionFinal() + " puntos");
        }
    }
}