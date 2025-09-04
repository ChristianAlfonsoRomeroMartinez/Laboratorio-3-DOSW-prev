package edu.dosw.lab.planningPoker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase TeamMemberFactory y la clase interna TeamMember.
 */
@DisplayName("TeamMemberFactory - Pruebas")
public class TeamMemberFactoryTest {

    @Test
    @DisplayName("Prueba de creación de miembros del equipo")
    public void testCreateTeamMembers() {
        // Crear un Scanner simulado
        Scanner scanner = new Scanner(""); // Scanner vacío para esta prueba
        
        // Obtener los miembros del equipo
        List<TeamMemberFactory.TeamMember> miembros = TeamMemberFactory.createTeamMembers(scanner);
        
        // Verificar que se crearon los 3 miembros esperados
        assertEquals(3, miembros.size(), "Deben crearse 3 miembros del equipo");
        
        // Verificar los datos del primer miembro
        TeamMemberFactory.TeamMember primero = miembros.get(0);
        assertEquals("Juana", primero.getNombre(), "El nombre del primer miembro debe ser 'Juana'");
        assertEquals("Desarrolladora", primero.getRol(), "El rol del primer miembro debe ser 'Desarrolladora'");
        
        // Verificar los datos del segundo miembro
        TeamMemberFactory.TeamMember segundo = miembros.get(1);
        assertEquals("Carlos", segundo.getNombre(), "El nombre del segundo miembro debe ser 'Carlos'");
        assertEquals("Tester", segundo.getRol(), "El rol del segundo miembro debe ser 'Tester'");
        
        // Verificar los datos del tercer miembro
        TeamMemberFactory.TeamMember tercero = miembros.get(2);
        assertEquals("Christian", tercero.getNombre(), "El nombre del tercer miembro debe ser 'Christian'");
        assertEquals("Analista", tercero.getRol(), "El rol del tercer miembro debe ser 'Analista'");
    }
    
    @Test
    @DisplayName("Prueba de validación de votos válidos")
    public void testEsVotoValido() throws Exception {
        // Usar reflexión para acceder al método privado
        java.lang.reflect.Method method = TeamMemberFactory.TeamMember.class.getDeclaredMethod("esVotoValido", int.class);
        method.setAccessible(true);
        
        // Crear un miembro para invocar el método
        TeamMemberFactory.TeamMember miembro = new TeamMemberFactory.TeamMember("Test", "Tester", new Scanner(""));
        
        // Comprobar votos válidos de la secuencia Fibonacci
        assertTrue((boolean) method.invoke(miembro, 1), "1 debe ser un voto válido");
        assertTrue((boolean) method.invoke(miembro, 2), "2 debe ser un voto válido");
        assertTrue((boolean) method.invoke(miembro, 3), "3 debe ser un voto válido");
        assertTrue((boolean) method.invoke(miembro, 5), "5 debe ser un voto válido");
        assertTrue((boolean) method.invoke(miembro, 8), "8 debe ser un voto válido");
        assertTrue((boolean) method.invoke(miembro, 13), "13 debe ser un voto válido");
        
        // Comprobar votos inválidos
        assertFalse((boolean) method.invoke(miembro, 0), "0 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 4), "4 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 6), "6 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 7), "7 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 9), "9 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 10), "10 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 11), "11 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 12), "12 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 14), "14 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 20), "20 debe ser un voto inválido");
        assertFalse((boolean) method.invoke(miembro, 100), "100 debe ser un voto inválido");
    }
    
    @Test
    @DisplayName("Prueba del método votar con entrada válida")
    public void testVotarValido() {
        // Simular entrada con un voto válido
        String input = "5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        
        // Crear miembro y historia
        TeamMemberFactory.TeamMember miembro = new TeamMemberFactory.TeamMember("Test", "Tester", scanner);
        UserStory historia = new UserStory("HU01", "actor", "objetivo", "beneficio", "calidad");
        
        // Realizar votación
        int voto = miembro.votar(historia);
        
        // Verificar resultado
        assertEquals(5, voto, "El voto debe ser 5");
    }
    
    @Test
    @DisplayName("Prueba del método votar con entradas inválidas y luego válida")
    public void testVotarInvalidoLuegoValido() {
        // Simular entrada con votos inválidos seguidos de uno válido
        // Primero un valor fuera del rango, luego un texto, finalmente un voto válido
        String input = "7\nabc\n3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        
        // Crear miembro y historia
        TeamMemberFactory.TeamMember miembro = new TeamMemberFactory.TeamMember("Test", "Tester", scanner);
        UserStory historia = new UserStory("HU01", "actor", "objetivo", "beneficio", "calidad");
        
        // Realizar votación - debería seguir pidiendo valores hasta que sea válido
        int voto = miembro.votar(historia);
        
        // Verificar resultado
        assertEquals(3, voto, "El voto final debe ser 3 después de rechazar entradas inválidas");
    }
}
