package edu.dosw.lab.planningPoker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase PlanningPokerMain.
 */
@DisplayName("PlanningPokerMain - Pruebas")
public class PlanningPokerMainTest {

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
    @DisplayName("Prueba del método obtenerHistoriasUsuario")
    public void testObtenerHistoriasUsuario() throws Exception {
        // Usamos reflexión para acceder al método privado
        java.lang.reflect.Method method = PlanningPokerMain.class.getDeclaredMethod("obtenerHistoriasUsuario");
        method.setAccessible(true);
        
        @SuppressWarnings("unchecked")
        List<UserStory> historias = (List<UserStory>) method.invoke(null);
        
        // Verifica que hay 6 historias (5 predefinidas + 1 adicional)
        assertEquals(6, historias.size(), "Debe retornar 6 historias de usuario");
        
        // Verifica la historia adicional (HU06)
        UserStory ultimaHistoria = historias.get(5);
        assertEquals("HU06", ultimaHistoria.getId(), "La última historia debe ser HU06");
        assertEquals("cliente", ultimaHistoria.getActor(), "El actor de HU06 debe ser 'cliente'");
        assertEquals("transferir dinero entre cuentas", ultimaHistoria.getObjetivo());
        assertEquals("poder mover fondos fácilmente", ultimaHistoria.getBeneficio());
        assertEquals("Funcionalidad", ultimaHistoria.getAtributoCalidad());
    }

    @Test
    @DisplayName("Prueba de integración de ejecución de Planning Poker")
    public void testExecutePlanningPokerIntegration() {
        // Simula entradas para 3 miembros del equipo, 6 historias, todos votando igual (5) para alcanzar consenso
        String simulatedInput = 
            "5\n"  // Juana vota en HU01
            + "5\n" // Carlos vota en HU01  
            + "5\n" // Christian vota en HU01
            + "8\n" // Juana vota en HU02
            + "8\n" // Carlos vota en HU02
            + "8\n" // Christian vota en HU02
            + "3\n" // Juana vota en HU03
            + "3\n" // Carlos vota en HU03
            + "3\n" // Christian vota en HU03
            + "13\n" // Juana vota en HU04
            + "13\n" // Carlos vota en HU04
            + "13\n" // Christian vota en HU04
            + "2\n" // Juana vota en HU05
            + "2\n" // Carlos vota en HU05
            + "2\n" // Christian vota en HU05
            + "1\n" // Juana vota en HU06
            + "1\n" // Carlos vota en HU06
            + "1\n"; // Christian vota en HU06
            
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        // Ejecuta el método principal
        PlanningPokerMain.ejecutar();
        
        // Verifica la salida
        String output = outputCaptor.toString();
        
        // Verificar que la aplicación ha iniciado correctamente
        assertTrue(output.contains("Bienvenido al sistema de Planning Poker para Bankify"), 
                   "La aplicación debe mostrar mensaje de bienvenida");
        
        // Verificar que se alcanzó consenso en cada historia
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 5"), 
                   "Debe mostrar consenso para la primera historia");
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 8"), 
                   "Debe mostrar consenso para la segunda historia");
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 3"), 
                   "Debe mostrar consenso para la tercera historia");
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 13"), 
                   "Debe mostrar consenso para la cuarta historia");
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 2"), 
                   "Debe mostrar consenso para la quinta historia");
        assertTrue(output.contains("¡Consenso alcanzado! Puntuación final: 1"), 
                   "Debe mostrar consenso para la sexta historia");
        
        // Verificar que se muestra el resumen de estimaciones
        assertTrue(output.contains("=== RESUMEN DE ESTIMACIONES ==="), 
                   "Debe mostrar la sección de resumen");
        assertTrue(output.contains("- HU01: 5 puntos"), 
                   "Debe mostrar la estimación final para HU01");
        assertTrue(output.contains("- HU06: 1 puntos"), 
                   "Debe mostrar la estimación final para HU06");
        
        // Verificar que se muestra el mensaje de despedida
        assertTrue(output.contains("Sesión finalizada. ¡Gracias por usar Planning Poker!"), 
                   "Debe mostrar mensaje de despedida");
    }
    
    @Test
    @DisplayName("Prueba del método main")
    public void testMain() {
        // Simula entrada para que termine rápido
        String simulatedInput = "5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        // Ejecuta el método main
        PlanningPokerMain.main(new String[]{});
        
        // Verifica que el método main llama a ejecutar() correctamente
        String output = outputCaptor.toString();
        
        assertTrue(output.contains("Bienvenido al sistema de Planning Poker para Bankify"), 
                   "El método main debe llamar a ejecutar() y mostrar bienvenida");
        assertTrue(output.contains("Sesión finalizada. ¡Gracias por usar Planning Poker!"), 
                   "El método main debe llamar a ejecutar() y mostrar despedida");
    }
}
