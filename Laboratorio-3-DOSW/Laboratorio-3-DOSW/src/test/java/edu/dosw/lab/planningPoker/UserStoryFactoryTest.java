package edu.dosw.lab.planningPoker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase UserStoryFactory.
 */
@DisplayName("UserStoryFactory - Pruebas")
public class UserStoryFactoryTest {

    @Test
    @DisplayName("Prueba de creación de historias de Bankify")
    public void testCreateBankifyUserStories() {
        // Obtener las historias predefinidas
        List<UserStory> historias = UserStoryFactory.createBankifyUserStories();
        
        // Verificar que se crearon las 5 historias predefinidas
        assertEquals(5, historias.size(), "Debe crear 5 historias predefinidas");
        
        // Verificar la primera historia
        UserStory primera = historias.get(0);
        assertEquals("HU01", primera.getId(), "El ID de la primera historia debe ser 'HU01'");
        assertEquals("cliente", primera.getActor(), "El actor de la primera historia debe ser 'cliente'");
        assertEquals("crear una cuenta bancaria", primera.getObjetivo());
        assertEquals("poder acceder a los servicios financieros", primera.getBeneficio());
        assertEquals("Usabilidad", primera.getAtributoCalidad());
        
        // Verificar la última historia
        UserStory ultima = historias.get(4);
        assertEquals("HU05", ultima.getId(), "El ID de la quinta historia debe ser 'HU05'");
        assertEquals("administrador", ultima.getActor(), "El actor de la quinta historia debe ser 'administrador'");
        assertTrue(ultima.getObjetivo().contains("gestionar los bancos"), "El objetivo debe contener 'gestionar los bancos'");
    }
    
    @Test
    @DisplayName("Prueba de creación de historias personalizadas")
    public void testCreateCustomUserStories() {
        List<UserStory> historias = UserStoryFactory.createCustomUserStories();
        
        // Inicialmente no debe haber historias personalizadas
        assertEquals(0, historias.size(), "Inicialmente no debe haber historias personalizadas");
    }
    
    @Test
    @DisplayName("Prueba de creación de todas las historias")
    public void testCreateAllUserStories() {
        List<UserStory> historias = UserStoryFactory.createAllUserStories();
        
        // Debe contener al menos las 5 historias predefinidas
        assertEquals(5, historias.size(), "Debe contener al menos las 5 historias predefinidas");
        
        // Verificar que la primera es la HU01
        assertEquals("HU01", historias.get(0).getId(), "La primera historia debe ser HU01");
    }
    
    @Test
    @DisplayName("Prueba de creación de historia individual")
    public void testCreateUserStory() {
        // Datos de prueba
        String id = "HU99";
        String actor = "desarrollador";
        String objetivo = "ejecutar pruebas automatizadas";
        String beneficio = "garantizar la calidad del código";
        String atributoCalidad = "Testeabilidad";
        
        // Crear la historia
        UserStory historia = UserStoryFactory.createUserStory(id, actor, objetivo, beneficio, atributoCalidad);
        
        // Verificar que se creó correctamente
        assertNotNull(historia, "La historia creada no debe ser null");
        assertEquals(id, historia.getId());
        assertEquals(actor, historia.getActor());
        assertEquals(objetivo, historia.getObjetivo());
        assertEquals(beneficio, historia.getBeneficio());
        assertEquals(atributoCalidad, historia.getAtributoCalidad());
    }
}
