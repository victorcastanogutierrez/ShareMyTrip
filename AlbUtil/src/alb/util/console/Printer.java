package alb.util.console;

import java.io.PrintStream;

/**
 * MÃ©todos de utilidad para escribir cosas en pantalla de forma controlada.
 * Aquï¿½ irian todas las decoraciones pertinentes
 * 
 * @author alb
 */
public class Printer {
	private static PrintStream con = System.out;
	
	public static void printHeading(String string) {
		con.println(string);
	}

	/**
	 * Avisa de error lógico en la ejecución, muy probablemente por 
	 * equivocacion del usuario o por circunstancias que han cambiado 
	 * durante el "think time" del usuario (control optimista y eso...)
	 * 
	 * @param e
	 */
	public static void printBusinessException(Exception e) {
		//Modificado
		con.println("\n\t" + e.getLocalizedMessage());
		con.println("\tVuelves al menú");
	}

	/**
	 * Avisa de un error por el que la aplicación debe cerrar
	 * @param e
	 */
	public static void printRuntimeException(RuntimeException e) {
		//Modificado
		con.println(e.getMessage());
	}

	public static void print(String msg) {
		con.println(msg);
	}

	public static void printException(String string, Exception e) {
		con.println(string);
		con.println("\t- " + e.getLocalizedMessage());
	}
}
