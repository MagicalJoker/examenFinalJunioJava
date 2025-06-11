package servicios;

/**
 * @author jmormez
 * 
 * Crea una aplicacion libre en java que cumpla los siguientes requisitos:
 * Operacion de alta de un elemento sobre una lista, operacion de baja de un
 * elemento sobre una lista en base a un campo de busqueda, uso de un fichero
 * como base de datos usando como delimitador el caracter punto y coma(;) El
 * fichero debe tener cabecera con el nombre de los campos. Este fichero se
 * cargará cada vez que se active la aplicación y será sobreescrito cada vez que
 * la aplicación se cierre. La carga se realizará en la lista estática tipo dto
 * y la sobreescritura se hará a partir de la información guardada en esa lista.
 * Las direcciones de los ficheros deberán ser relativas y apuntar a carpetas
 * dentro del proyecto para que la aplicación pueda ser ejecutada en cualquier
 * equipo sin que sea necesario modificar las direcciones. La aplicación contará
 * con un fichero log en el que quedará registrada la navegación del usuario por
 * las diferentes opciones y la traza de errores.
 */
public class MenuImplementacion implements MenuInterfaz {
	
	public void mostrarMenu() {
		
		System.out.println("1.- Fichar jugador");
		System.out.println("2.- Quitar jugador de la plantilla");
		System.out.println("3.- Mostrar plantilla");
		System.out.println("0.- Salir");
		
	}
	

}
