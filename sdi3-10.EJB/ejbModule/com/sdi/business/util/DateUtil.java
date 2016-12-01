package com.sdi.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase de utilidad con fechas
 * 
 * @author sdi2-10
 * 
 */
public class DateUtil {

	/**
	 * Obtiene, formateada un string con la fecha actual
	 * 
	 * @return string con la fecha
	 */
	public static String fechaHoy() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
