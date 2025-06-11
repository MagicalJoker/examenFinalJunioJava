package dtos;

public class JugadoresDTO {
    
    private static int contadorID = 1;
    private int id;
    private String nombre;
    private String posicion;
    private String nacionalidad;
    
    /**
     * Constructor
     * @author jmormez
     * @param nombre
     * @param posicion
     * @param nacionalidad
     */
    public JugadoresDTO(String nombre, String posicion, String nacionalidad) {
        this.id = contadorID++;
        this.nombre = nombre;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
    }
    
    /**
     * Constructor con ID específico (para cargar desde archivo)
     * @param id
     * @param nombre
     * @param posicion
     * @param nacionalidad
     */
    public JugadoresDTO(int id, String nombre, String posicion, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        // Actualizar contador si es necesario
        if (id >= contadorID) {
            contadorID = id + 1;
        }
    }
    
    /**
     * Getters & Setters
     * @author jmormez
     */
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPosicion() {
        return posicion;
    }
    
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    /**
     * Método toString
     * @author jmormez
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Posición: " + posicion + " | Nacionalidad: " + nacionalidad;
    }
    
    /**
     * Método para convertir a formato CSV
     */
    public String toCSV() {
        return id + ";" + nombre + ";" + posicion + ";" + nacionalidad;
    }
}