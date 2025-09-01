package edu.dosw.lab.planningpoker;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es una fábrica para crear historias de usuario.
 * Tiene historias predefinidas de Bankify y puede crear historias personalizadas.
 * Usa el patrón Factory para organizar la creación de historias de manera centralizada.
 */
public class UserStoryFactory {
    // Matriz con historias predefinidas [id, actor, objetivo, beneficio, atributoCalidad]
    private static final String[][] HISTORIAS_BANKIFY = {
        {"HU01", "cliente", "crear una cuenta bancaria", "poder acceder a los servicios financieros", "Usabilidad"},
        {"HU02", "cliente", "que el sistema valide mi número de cuenta", "asegurarme de que mi cuenta es válida", "Confiabilidad"},
        {"HU03", "cliente", "consultar el saldo de mi cuenta", "conocer mi disponibilidad de fondos", "Disponibilidad"},
        {"HU04", "cliente", "realizar un depósito en mi cuenta", "incrementar mi saldo", "Seguridad"},
        {"HU05", "administrador", "gestionar los bancos registrados", "asegurar que solo se usen bancos autorizados", "Mantenibilidad"}
    };
    
    // Crea y devuelve las historias de usuario predefinidas para Bankify
    /**
     * Crea y devuelve las historias de usuario predefinidas para Bankify.
     * Estas son las historias base que siempre están disponibles.
     * @return Una lista con las 5 historias predefinidas de Bankify
     */
    public static List<UserStory> createBankifyUserStories() {
        List<UserStory> historias = new ArrayList<>();
        for (String[] datos : HISTORIAS_BANKIFY) {
            historias.add(new UserStory(datos[0], datos[1], datos[2], datos[3], datos[4]));
        }
        return historias;
    }
    
    // Permite agregar historias personalizadas
    public static List<UserStory> createCustomUserStories() {
        List<UserStory> historias = new ArrayList<>();
        return historias;
    }
    
    // Combina historias predefinidas y personalizadas
    public static List<UserStory> createAllUserStories() {
        List<UserStory> todas = createBankifyUserStories();
        todas.addAll(createCustomUserStories());
        return todas;
    }
    
    // Método para crear una historia individual
    /**
     * Método para crear una historia individual con parámetros específicos.
     * Útil para agregar historias personalizadas o adicionales.
     * @param id El identificador único de la historia
     * @param actor Quién quiere la funcionalidad
     * @param objetivo Qué quiere hacer
     * @param beneficio Por qué lo quiere
     * @param atributoCalidad Qué atributo de calidad se afecta
     * @return Una nueva instancia de UserStory con los datos proporcionados
     */
    public static UserStory createUserStory(String id, String actor, String objetivo, 
                                           String beneficio, String atributoCalidad) {
        return new UserStory(id, actor, objetivo, beneficio, atributoCalidad);
    }
}