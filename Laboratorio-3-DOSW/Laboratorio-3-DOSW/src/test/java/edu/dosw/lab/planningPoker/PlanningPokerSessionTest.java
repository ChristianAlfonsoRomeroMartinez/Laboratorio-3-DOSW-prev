package edu.dosw.lab.planningPoker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase PlanningPokerSession.
 */
@DisplayName("PlanningPokerSession - Pruebas")
public class PlanningPokerSessionTest {

    private final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputCaptor));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    
    @Test
    @DisplayName("Prueba de creación de sesión")
    public void testCrearSesion() {
        // Crear datos para la sesión
        List<UserStory> historias = new ArrayList<>();
        historias.add(new UserStory("HU01", "actor", "objetivo", "beneficio", "calidad"));
        
        // Simular entrada para que termine rápido cuando llame a votar
        String input = "5\n5\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        // Crear sesión
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        
        // Verificar que la sesión se creó correctamente
        assertNotNull(sesion, "La sesión no debe ser null");
    }
    
    @Test
    @DisplayName("Prueba del método hayConsenso")
    public void testHayConsenso() throws Exception {
        // Usar reflexión para acceder al método privado
        java.lang.reflect.Method method = PlanningPokerSession.class.getDeclaredMethod("hayConsenso", Map.class);
        method.setAccessible(true);
        
        // Crear una sesión simple para invocar el método
        List<UserStory> historias = new ArrayList<>();
        Scanner scanner = new Scanner("");
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        
        // Probar con mapa vacío
        Map<String, Integer> votosVacios = new HashMap<>();
        assertFalse((boolean) method.invoke(sesion, votosVacios), "No debe haber consenso en un mapa vacío");
        
        // Probar con un solo voto
        Map<String, Integer> votosUnico = new HashMap<>();
        votosUnico.put("Juana", 5);
        assertTrue((boolean) method.invoke(sesion, votosUnico), "Debe haber consenso con un solo voto");
        
        // Probar con votos iguales
        Map<String, Integer> votosIguales = new HashMap<>();
        votosIguales.put("Juana", 5);
        votosIguales.put("Carlos", 5);
        votosIguales.put("Christian", 5);
        assertTrue((boolean) method.invoke(sesion, votosIguales), "Debe haber consenso con votos iguales");
        
        // Probar con votos diferentes
        Map<String, Integer> votosDiferentes = new HashMap<>();
        votosDiferentes.put("Juana", 5);
        votosDiferentes.put("Carlos", 8);
        votosDiferentes.put("Christian", 5);
        assertFalse((boolean) method.invoke(sesion, votosDiferentes), "No debe haber consenso con votos diferentes");
    }
    
    @Test
    @DisplayName("Prueba de estimación de historia con consenso inmediato")
    public void testEstimarHistoriaConsensoInmediato() throws Exception {
        // Crear una historia
        UserStory historia = new UserStory("HU01", "actor", "objetivo", "beneficio", "calidad");
        List<UserStory> historias = new ArrayList<>();
        historias.add(historia);
        
        // Simular votación donde todos votan 5
        String input = "5\n5\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        // Crear sesión
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        
        // Usar reflexión para acceder al método privado
        java.lang.reflect.Method method = PlanningPokerSession.class.getDeclaredMethod("estimarHistoria", UserStory.class);
        method.setAccessible(true);
        
        // Estimar la historia
        method.invoke(sesion, historia);
        
        // Verificar que la historia tiene la estimación correcta
        assertEquals(5, historia.getEstimacionFinal(), "La estimación final debe ser 5");
        
        // Verificar la salida
        String output = outputCaptor.toString();
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 5"), "Debe mostrar mensaje de consenso");
    }
    
    @Test
    @DisplayName("Prueba de estimación de historia sin consenso inicial")
    public void testEstimarHistoriaSinConsensoInicial() throws Exception {
        // Crear una historia
        UserStory historia = new UserStory("HU01", "actor", "objetivo", "beneficio", "calidad");
        List<UserStory> historias = new ArrayList<>();
        historias.add(historia);
        
        // Simular votación donde primero votan diferente y luego igual
        // Primera ronda: Juana=5, Carlos=8, Christian=5
        // Segunda ronda: todos=5
        String input = "5\n8\n5\n5\n5\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        // Crear sesión
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        
        // Usar reflexión para acceder al método privado
        java.lang.reflect.Method method = PlanningPokerSession.class.getDeclaredMethod("estimarHistoria", UserStory.class);
        method.setAccessible(true);
        
        // Estimar la historia
        method.invoke(sesion, historia);
        
        // Verificar que la historia tiene la estimación correcta
        assertEquals(5, historia.getEstimacionFinal(), "La estimación final debe ser 5");
        
        // Verificar la salida
        String output = outputCaptor.toString();
        assertTrue(output.contains("Votos divergentes"), "Debe mostrar mensaje de votos divergentes");
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 5"), "Debe mostrar mensaje de consenso al final");
    }
    
    @Test
    @DisplayName("Prueba de iniciar sesión completa")
    public void testIniciarSesion() {
        // Crear historias
        List<UserStory> historias = new ArrayList<>();
        historias.add(new UserStory("HU01", "actor", "objetivo1", "beneficio1", "calidad1"));
        historias.add(new UserStory("HU02", "actor", "objetivo2", "beneficio2", "calidad2"));
        
        // Simular votación donde todos votan igual en todas las historias
        String input = "5\n5\n5\n8\n8\n8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        
        // Crear e iniciar sesión
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        sesion.iniciarSesion();
        
        // Verificar que las historias tienen las estimaciones correctas
        assertEquals(5, historias.get(0).getEstimacionFinal(), "La primera historia debe tener estimación 5");
        assertEquals(8, historias.get(1).getEstimacionFinal(), "La segunda historia debe tener estimación 8");
        
        // Verificar la salida
        String output = outputCaptor.toString();
        assertTrue(output.contains("=== SESIÓN DE PLANNING POKER ==="), "Debe mostrar título de la sesión");
        assertTrue(output.contains("=== RESUMEN DE ESTIMACIONES ==="), "Debe mostrar resumen de estimaciones");
        assertTrue(output.contains("- HU01: 5 puntos"), "Debe mostrar estimación de HU01");
        assertTrue(output.contains("- HU02: 8 puntos"), "Debe mostrar estimación de HU02");
    }
    
    @Test
    @DisplayName("Prueba de mostrar resumen")
    public void testMostrarResumen() throws Exception {
        // Crear historias con estimaciones
        List<UserStory> historias = new ArrayList<>();
        UserStory hu1 = new UserStory("HU01", "actor", "objetivo1", "beneficio1", "calidad1");
        hu1.setEstimacionFinal(5);
        UserStory hu2 = new UserStory("HU02", "actor", "objetivo2", "beneficio2", "calidad2");
        hu2.setEstimacionFinal(8);
        historias.add(hu1);
        historias.add(hu2);
        
        // Crear sesión
        Scanner scanner = new Scanner("");
        PlanningPokerSession sesion = new PlanningPokerSession(historias, scanner);
        
        // Usar reflexión para acceder al método privado
        java.lang.reflect.Method method = PlanningPokerSession.class.getDeclaredMethod("mostrarResumen");
        method.setAccessible(true);
        
        // Mostrar resumen
        method.invoke(sesion);
        
        // Verificar la salida
        String output = outputCaptor.toString();
        assertTrue(output.contains("=== RESUMEN DE ESTIMACIONES ==="), "Debe mostrar título del resumen");
        assertTrue(output.contains("- HU01: 5 puntos"), "Debe mostrar estimación de HU01");
        assertTrue(output.contains("- HU02: 8 puntos"), "Debe mostrar estimación de HU02");
    }
}
