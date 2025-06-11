package servicios;
/**
 * Interfaz para las operaciones de la aplicación
 * @author jmormez
 */

public interface OperativaInterfaz {

	    /**
	     * Ficha un nuevo jugador al equipo
	     */
	    void ficharJugador();
	    
	    /**
	     * Elimina un jugador del equipo por ID
	     */
	    void eliminarJugador();
	    
	    /**
	     * Muestra la plantilla actual
	     */
	    void mostrarPlantilla();
	    
	    /**
	     * Carga los datos desde el archivo CSV
	     */
	    void cargarDatos();
	    
	    /**
	     * Guarda los datos en el archivo CSV
	     */
	    void guardarDatos();
	    
	    /**
	     * Escribe en el archivo de log
	     * @param mensaje
	     */
	    void escribirLog(String mensaje);
	}


