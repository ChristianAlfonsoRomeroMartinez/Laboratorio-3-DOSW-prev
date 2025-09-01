package edu.dosw.lab.planningpoker;

/**
 * Esta clase representa una historia de usuario en el sistema.
 * Es como una tarjeta con la descripción de lo que quiere el usuario.
 * Tiene toda la información necesaria: quién, qué quiere, por qué y qué atributo de calidad.
 */
public class UserStory {
    private String id;
    private String actor;
    private String objetivo;
    private String beneficio;
    private String atributoCalidad;
    private int estimacionFinal;
    
    /**
     * Constructor que crea una nueva historia de usuario con toda su información.
     * @param id El identificador único de la historia (ej: HU01)
     * @param actor Quién quiere la funcionalidad (ej: cliente)
     * @param objetivo Qué quiere hacer el actor
     * @param beneficio Por qué quiere hacerlo
     * @param atributoCalidad Qué calidad del sistema se ve afectada
     */
    public UserStory(String id, String actor, String objetivo, String beneficio, String atributoCalidad) {
        this.id = id;
        this.actor = actor;
        this.objetivo = objetivo;
        this.beneficio = beneficio;
        this.atributoCalidad = atributoCalidad;
        this.estimacionFinal = 0;
    }
    
    // Getters
    public String getId() { return id; }
    public String getActor() { return actor; }
    public String getObjetivo() { return objetivo; }
    public String getBeneficio() { return beneficio; }
    public String getAtributoCalidad() { return atributoCalidad; }
    public int getEstimacionFinal() { return estimacionFinal; }
    
    // Setter para la estimación
    public void setEstimacionFinal(int estimacionFinal) {
        this.estimacionFinal = estimacionFinal;
    }
    
    /**
     * Convierte la historia a un texto legible para mostrar en consola.
     * Formatea toda la información de manera clara y organizada.
     * @return Un string con la descripción completa de la historia
     */
    @Override
    public String toString() {
        return String.format("Historia %s: Como %s, quiero %s, para %s [Atributo: %s]", 
                            id, actor, objetivo, beneficio, atributoCalidad);
    }
}