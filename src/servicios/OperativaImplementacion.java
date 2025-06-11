package servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


import controladores.Inicio;
import dtos.JugadoresDTO;

/**
 * Implementación de las operaciones de la aplicación
 * @author jmormez
 */
public class OperativaImplementacion implements  OperativaInterfaz {
    
    private final String ARCHIVO_DATOS = "data/jugadores.txt";
    private final String CARPETA_LOGS = "logs";
    
    
    /**
     * Metodo para añadir un nuevo jugador a tu plantilla, Hay un maximo establecido de 11 jugadores
     * @author jmormez
     * @param args
     */
    @Override
    public void ficharJugador() {
        escribirLog("OPERACIÓN: Iniciando fichaje de jugador");
        
        if (Inicio.jugadores.size() >= 11) {
            System.out.println("La plantilla está completa, No se pueden fichar más jugadores.");
            escribirLog("ADVERTENCIA: Intento de fichaje jugador con plantilla completa");
            return;
        }
        
        try {
            System.out.println("\n=== FICHAR NUEVO JUGADOR ===");
            
            System.out.print("Nombre del jugador: ");
            String nombre = Inicio.sc.nextLine().trim();
            
            //El nombre no puede estar vacio
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return;
            }
            
            System.out.println("Posiciones disponibles: POR, DFC, LI, LD, MCD, MC, MI, MD, MCO, EI, ED, DC");
            System.out.print("Posición: ");
            String posicion = Inicio.sc.nextLine().trim().toUpperCase();//agrego un toUpperCase por si el usuario lo introduce en minusculas
            
           
            
            System.out.print("Nacionalidad: ");
            String nacionalidad = Inicio.sc.nextLine().trim();
            
            if (nacionalidad.isEmpty()) {
                System.out.println("La nacionalidad no puede estar vacía.");
                return;
            }
            
            JugadoresDTO jugadorNuevo = new JugadoresDTO(nombre, posicion, nacionalidad);
            Inicio.jugadores.add(jugadorNuevo);
            
            System.out.println("Jugador fichado exitosamente");
            System.out.println(jugadorNuevo);
            
            escribirLog("ÉXITO: Jugador fichado " + jugadorNuevo.toString());
            
        } catch (Exception e) {
            System.out.println("Error al fichar jugador: " + e.getMessage());
            escribirLog("ERROR: Al fichar jugador - " + e.getMessage());
        }
    }
    
    /**
     * Metodo para eliminar un jugador de la plantilla
     * @author jmormez
     * @param args
     */
    @Override
    public void eliminarJugador() {
        escribirLog("OPERACIÓN: Iniciando eliminación de jugador");
        
        if (Inicio.jugadores.isEmpty()) {
            System.out.println("No hay jugadores en la plantilla.");
            escribirLog("ADVERTENCIA: Intento de eliminar jugador de plantilla vacía");
            return;
        }
        
        try {
            System.out.println("\n=== ELIMINAR JUGADOR ===");
            
            System.out.print("Introduzca el ID del jugador a eliminar: ");
            int idEliminar = Inicio.sc.nextInt();
            Inicio.sc.nextLine(); // Limpiar buffer
            
            boolean encontrado = false;
            for (int i = 0; i < Inicio.jugadores.size(); i++) {
                if (Inicio.jugadores.get(i).getId() == idEliminar) {
                    JugadoresDTO jugadorEliminado = Inicio.jugadores.get(i);
                    Inicio.jugadores.remove(i);
                    System.out.println("Jugador eliminado: " + jugadorEliminado.getNombre());
                    escribirLog("eXITO: Jugador eliminado - " + jugadorEliminado.toString());
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) {
                System.out.println("No se encontró ningún jugador con ese ID.");
                escribirLog("ERROR: ID no encontrado - " + idEliminar);
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar jugador: " + e.getMessage());
            escribirLog("ERROR: Al eliminar jugador - " + e.getMessage());
            Inicio.sc.nextLine(); // Limpiar buffer en caso de error
        }
    }
    
    
    /**
     * Metodo para mostrar por pantalla los jugadores de tu plantilla.
     * @author jmormez
     * @param args
     */
    @Override
    public void mostrarPlantilla() {
        escribirLog("OPERACIÓN: Mostrando plantilla");
        
        System.out.println("\n=== PLANTILLA ACTUAL ===");
        
        if (Inicio.jugadores.isEmpty()) {
            System.out.println("No hay jugadores en la plantilla.Via libre para hacerte un Chelsea");
        } else {
            System.out.println("Jugadores fichados (" + Inicio.jugadores.size() + "/11):");
            System.out.println("----------------------------------------");
            for (JugadoresDTO jugador : Inicio.jugadores) {
                System.out.println(jugador);
            }
        }
        System.out.println("========================");
    }
    
    
    /**
     * Metodo de carga de datos. Sirve para tener los datos listos al iniciar la aplicación.
     * @author jmormez
     * @param args 
     */
    @Override
    public void cargarDatos() {
        escribirLog("OPERACIÓN: Cargando datos desde archivo");
        
        File archivo = new File(ARCHIVO_DATOS);
        
        if (!archivo.exists()) {
            // Si no existe el archivo, crear datos iniciales
            crearDatosIniciales();
            escribirLog("INFO: Archivo no existe, creando datos iniciales");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // Leer cabecera
            
            if (linea == null || !linea.equals("id;nombre;posicion;nacionalidad")) {
                System.out.println("Formato de archivo incorrecto. Creando archivo nuevo...");
                escribirLog("ERROR: Formato de archivo incorrecto");
                crearDatosIniciales();
                return;
            }
            
            Inicio.jugadores.clear();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";"); //Aquí especifico que quiero usar el separador punto y coma.
                if (datos.length == 4) {
                    try {
                        int id = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        String posicion = datos[2];
                        String nacionalidad = datos[3];
                        
                        JugadoresDTO jugador = new JugadoresDTO(id, nombre, posicion, nacionalidad);
                        Inicio.jugadores.add(jugador);
                    } catch (NumberFormatException e) {
                        escribirLog("ERROR: Error al parsear ID en línea: " + linea);
                    }
                }
            }
            
            System.out.println("Datos cargados correctamente. Jugadores: " + Inicio.jugadores.size());
            escribirLog("ÉXITO: Datos cargados - " + Inicio.jugadores.size() + " jugadores");
            
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            escribirLog("ERROR: Al cargar datos - " + e.getMessage());
            crearDatosIniciales();
        }
    }
    
    /**
     * Metodo para guardar los datos en el archivo jugadores.txt
     * @author jmormez
     * @param args
     */
    @Override
    public void guardarDatos() {
        escribirLog("OPERACIÓN: Guardando datos en archivo");
        
        try {
            // Crear directorio si no existe
            File directorio = new File("data");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_DATOS))) {
                // Escribir cabecera
                bw.write("id;nombre;posicion;nacionalidad");
                bw.newLine();
                
                // Escribir datos de jugadores
                for (JugadoresDTO jugador : Inicio.jugadores) {
                    bw.write(jugador.toCSV()); //Formato CSV -> Separador punto y coma. 
                    bw.newLine();
                }
            }
            
    System.out.println("Datos guardados correctamente.");
            escribirLog("ÉXITO: Datos guardados - " + Inicio.jugadores.size() + " jugadores");
            
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
            escribirLog("ERROR: Al guardar datos - " + e.getMessage());
        }
    }
    
    
    /**
     * Metodo que especifica la estructura de las lineas de los archivos logs. 
     * @author jmormez
     * @param args
     */
    @Override
    public void escribirLog(String mensaje) {
        try {
            // Crear directorio de logs si no existe
            File directorioLogs = new File(CARPETA_LOGS);
            if (!directorioLogs.exists()) {
                directorioLogs.mkdirs();
            }
            
            // Crear nombre del archivo con fecha actual
            String fechaHoy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String nombreArchivoLog = CARPETA_LOGS + fechaHoy + ".log";
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoLog, true))) {
                String tiempoLog = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); //se crea un registro detallado con fecha y hora para las lineas del log.
                bw.write("[" + tiempoLog + "] " + mensaje);
                bw.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Error al escribir en log: " + e.getMessage());
        }
    }
    
   
    
    /**
     * Añadir datos iniciales del Real Madrid. Estos serán los 11 jugadores por defecto.
     */
    private void crearDatosIniciales() {
        Inicio.jugadores.clear();
        
        // Crear jugadores del Real Madrid
        Inicio.jugadores.add(new JugadoresDTO("Thibaut Courtois", "POR", "BEL"));
        Inicio.jugadores.add(new JugadoresDTO("Dani Carvajal", "LD", "ESP"));
        Inicio.jugadores.add(new JugadoresDTO("Éder Militao", "DFC", "BRA"));
        Inicio.jugadores.add(new JugadoresDTO("Antonio Rüdiger", "DFC", "GER"));
        Inicio.jugadores.add(new JugadoresDTO("Ferland Mendy", "LI", "FRA"));
        Inicio.jugadores.add(new JugadoresDTO("Jude Bellingham", "MCD", "ENG"));
        Inicio.jugadores.add(new JugadoresDTO("Luka Modrić", "MC", "CRO"));
        Inicio.jugadores.add(new JugadoresDTO("Federico Valverde", "MC", "UY"));
        Inicio.jugadores.add(new JugadoresDTO("Vinicius Jr.", "EI", "BRA"));
        Inicio.jugadores.add(new JugadoresDTO("Kylian Mbappe", "DC", "FRA"));
        Inicio.jugadores.add(new JugadoresDTO("Rodrygo", "ED", "BRA"));
        
        // Guardar datos iniciales
        guardarDatos();
        escribirLog("INFO: Datos iniciales creados con jugadores del Real Madrid");
    }
}
