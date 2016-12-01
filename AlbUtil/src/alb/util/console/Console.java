package alb.util.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	protected static BufferedReader kbd = new BufferedReader(
			new InputStreamReader(System.in));
	
	public static void println() {
		System.out.println();
	}

	public static void println(Object obj) {
		System.out.println( obj.toString() );
	}

	public static void println(String string) {
		System.out.println(string);
	}

	public static void print(String string) {
		System.out.print(string);
	}

	public static void printf(String format, Object... args) {
		System.out.printf(format, args);	
	}

	/**
	 * Método que lee un entero de consola
	 * @return el entero leido
	 * @throws NumberFormatException en caso de error en el formato o ser negativo
	 */
	public static Integer readInt() throws NumberFormatException {
		try {
			int n = Integer.parseInt(kbd.readLine());
			if (n < 0)
				throw new NumberFormatException ();
			return n;
		} catch (IOException ioe) {
			throw new NumberFormatException ();
		}
	}

	/**
	 * Método que lee un long de consola
	 * @return el long leido
	 * @throws NumberFormatException en caso de error en el formato
	 */
	public static Long readLong() throws NumberFormatException {
		try {
			
			return Long.parseLong(kbd.readLine());
			
		} catch (IOException ioe) {
			throw new NumberFormatException ();
		}
	}

	/**
	 * Método que lee un double de consola
	 * @return el double leido
	 * @throws NumberFormatException en caso de error en el formato o ser negativo
	 */
	public static Double readDouble() throws NumberFormatException {
		try {
			double b = Double.parseDouble(kbd.readLine());
			if (b < 0)
				throw new NumberFormatException ();
			return b;
			
		} catch (IOException ioe) {
			throw new NumberFormatException ();
		}
	}

	/**
	 * Método que lee un string de consola
	 * @return
	 */
	public static String readString() {
		try {
			return kbd.readLine();
		} catch (IOException e) {
			throw new NumberFormatException ();
		}
	}

	public static String readString(String msg) {
		print(msg + ": ");
		return readString();
	}

	public static Long readLong(String msg) {
		try {
			print(msg + ": ");
			return readLong();
		} catch (NumberFormatException e) {
			return readLong(msg);
		}
	}

	public static Integer readInt(String msg) {
		try {
			print(msg + ": ");
			return readInt();
		} catch (NumberFormatException e) {
			return readInt(msg);
		}
	}

	public static double readDouble(String msg) {
		try {
			print(msg + ": ");
			return readDouble();
		} catch (NumberFormatException e) {
			return readDouble(msg);
		}
	}

}
