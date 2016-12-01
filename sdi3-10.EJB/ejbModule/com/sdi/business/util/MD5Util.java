package com.sdi.business.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase de utilidad para encriptar una cadena a MD5
 * 
 * @author sdi2-10
 * 
 */
public class MD5Util {

	/**
	 * Método que dada una cadena la retorna encriptada en MD5
	 * 
	 * @param input
	 *            String para encriptar
	 * @return String encriptado
	 */
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
