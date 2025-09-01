package planningpoker;

public class UserStory {
    private String id;
    private String actor;
    private String objetivo;
    private String beneficio;
    private String atributoCalidad;
    private int estimacionFinal;
    
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
    
    @Override
    public String toString() {
        return String.format("Historia %s: Como %s, quiero %s, para %s [Atributo: %s]", 
                            id, actor, objetivo, beneficio, atributoCalidad);
    }
}