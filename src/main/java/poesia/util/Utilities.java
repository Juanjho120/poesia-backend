package poesia.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static DateTimeFormatter formatterEst = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	public static DateTimeFormatter formatterHourEst = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private Utilities() { }
	
	public static String darFormatoFechaSinHora(LocalDate fecha, Boolean estandar) {
		if(fecha!=null) {
			return darFormatoFechaSinHora(fecha.atStartOfDay(), estandar);
		}
		return null;
	}
	
	public static String darFormatoFechaSinHora(LocalDateTime fecha, Boolean estandar) {
		if(fecha!=null) {
			if(estandar) {
				return formatterEst.format(fecha);
			}
			return formatter.format(fecha);
		}
		return null;
	}
	
	public static String darFormatoFechaConHora(LocalDate fecha, Boolean estandar) {
		if(fecha!=null) {
			return darFormatoFechaConHora(fecha.atStartOfDay(), estandar);
		}
		return null;
	}
	
	public static String darFormatoFechaConHora(LocalDateTime fecha, Boolean estandar) {
		if(fecha!=null) {
			if(estandar) {
				return formatterHourEst.format(fecha);
			}
			return formatterHour.format(fecha);
		}
		return null;
	}
	
	public static LocalDateTime convertirAFechaSinHora(String fecha, String hora, Boolean estandar) {
		if(fecha!=null && hora!=null && !fecha.trim().equalsIgnoreCase("") && !hora.trim().equalsIgnoreCase("")) {
			fecha += " " + hora;
			return convertirAFechaConHora(fecha, estandar);
		}
		return null;
	}
	
	public static LocalDateTime convertirAFechaConHora(String fecha, Boolean estandar) {
		if(fecha!=null && !fecha.trim().equalsIgnoreCase("")) {
			if(estandar) {
				return LocalDateTime.parse(fecha, formatterHourEst);
			}
			return LocalDateTime.parse(fecha, formatterHour);
		}
		return null;
	}
	
	public static String firstLetterUpperCase(String texto) {
		if(texto.length()>0) {
			return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
		}
		return null;
	}
	
	public static String firstLetterEachWordUpperCase(String texto) {
		if(texto.length()>0) {
			String result = "";
			String words[] = texto.split("\\s");
			for(String word : words) {
				result += word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase() + " ";
			}
			return result;
		}
		return null;
	}
	
}
