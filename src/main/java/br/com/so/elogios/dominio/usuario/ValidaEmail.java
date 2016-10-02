package br.com.so.elogios.dominio.usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEmail {
	private final static String PATTERN_EMAIL_REGEX = "^(.+)@(.+)$";

	public static boolean validar(String email) {
		Pattern pattern = Pattern.compile(PATTERN_EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return !matcher.matches();
	}
	
}
