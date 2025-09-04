package edu.dosw.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Clase principal de pruebas que ejecuta todos los tests del proyecto.
 */
@DisplayName("AppTest - Pruebas Generales")
class AppTest {

    @Test
    @DisplayName("Prueba simple de aplicación")
    void testApp() {
        assertTrue(true);
    }
    
    @Test
    @DisplayName("Pruebas de Planning Poker")
    void testPlanningPoker() {
        // Las pruebas de Planning Poker se ejecutan automáticamente en su propio paquete
        // No necesitamos llamarlas explícitamente aquí
        assertTrue(true, "Las pruebas de Planning Poker deben ejecutarse automáticamente");
    }
}
