package controladores;

import java.util.ArrayList;
import java.util.Scanner;
import dtos.JugadoresDTO;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;
import servicios.OperativaImplementacion;
import servicios.OperativaInterfaz;

/**
 * Puerta de entrada a mi aplicación
 * @author jmormez
 * La aplicación tratará de hacer un dream-team de futbol. Registra hasta 11 jugadores para crear el mejor dream team.
 * La aplicacion tendra de manera precargada 11 jugadores del real madrid. Sustituyelos por verdaderas estrellas (si son del madrid mejor)
 */
public class Inicio {
    
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<JugadoresDTO> jugadores = new ArrayList<>();
    public static MenuInterfaz menu = new MenuImplementacion();
    public static OperativaInterfaz operativa = new OperativaImplementacion();
    
    /**
     * Clase controladora de mi aplicación
     * @author jmormez
     * @param args
     */
    public static void main(String[] args) {
        
        // Cargar datos del archivo al iniciar
        operativa.cargarDatos();
        
        boolean menuAbierto = true;
        int opcionUsuario;
        
        while (menuAbierto) {
            menu.mostrarMenu();
            
            try {
                opcionUsuario = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                
                switch (opcionUsuario) {
                    case 1: {
                        // Fichar jugador
                        operativa.ficharJugador();
                        break;
                    }
                    case 2: {
                        // Quitar jugador de la plantilla
                        operativa.eliminarJugador();
                        break;
                    }
                    case 3: {
                        // Mostrar plantilla
                        operativa.mostrarPlantilla();
                        break;
                    }
                    case 0: {
                        // Salir
                        operativa.guardarDatos();
                        System.out.println("¡Gracias por usar Dream Team Manager!");
                        menuAbierto = false;
                        break;
                    }
                    default: {
                        System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: Por favor, introduzca un número válido.");
                sc.nextLine(); // Limpiar buffer en caso de error
                operativa.escribirLog("ERROR: Entrada no válida - " + e.getMessage());
            }
        }
        
        sc.close();
    }
}