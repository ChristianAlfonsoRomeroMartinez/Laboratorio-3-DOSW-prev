package edu.dosw.lab.planningPoker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase UserStory.
 */
@DisplayName("UserStory - Pruebas")
public class UserStoryTest {

    @Test
    @DisplayName("Prueba de creación de historia de usuario")
    public void testCrearHistoriaUsuario() {
        // Datos de prueba
        String id = "HU01";
        String actor = "cliente";
        String objetivo = "crear una cuenta bancaria";
        String beneficio = "poder acceder a los servicios financieros";
        String atributoCalidad = "Usabilidad";
        
        // Crear la historia
        UserStory historia = new UserStory(id, actor, objetivo, beneficio, atributoCalidad);
        
        // Verificar que los datos se guardaron correctamente
        assertEquals(id, historia.getId(), "El ID debe ser 'HU01'");
        assertEquals(actor, historia.getActor(), "El actor debe ser 'cliente'");
        assertEquals(objetivo, historia.getObjetivo(), "El objetivo debe guardarse correctamente");
        assertEquals(beneficio, historia.getBeneficio(), "El beneficio debe guardarse correctamente");
        assertEquals(atributoCalidad, historia.getAtributoCalidad(), "El atributo de calidad debe ser 'Usabilidad'");
        
        // La estimación por defecto debe ser 0
        assertEquals(0, historia.getEstimacionFinal(), "La estimación inicial debe ser 0");
    }
    
    @Test
    @DisplayName("Prueba de establecer estimación final")
    public void testEstablecerEstimacionFinal() {
        // Crear una historia
        UserStory historia = new UserStory("HU01", "cliente", "objetivo", "beneficio", "calidad");
        
        // Establecer una estimación
        int estimacion = 8;
        historia.setEstimacionFinal(estimacion);
        
        // Verificar que se guardó correctamente
        assertEquals(estimacion, historia.getEstimacionFinal(), "La estimación final debe ser 8");
    }
    
    @Test
    @DisplayName("Prueba del método toString")
    public void testToString() {
        // Crear una historia con datos específicos
        UserStory historia = new UserStory("HU01", "cliente", "crear cuenta", "acceder a servicios", "Usabilidad");
        
        // Obtener la representación en string
        String resultado = historia.toString();
        
        // Verificar el formato
        String esperado = "Historia HU01: Como cliente, quiero crear cuenta, para acceder a servicios [Atributo: Usabilidad]";
        assertEquals(esperado, resultado, "El formato de toString debe ser correcto");
    }
}
