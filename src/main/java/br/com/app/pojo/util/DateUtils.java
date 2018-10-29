package br.com.app.pojo.util;

import java.time.LocalDate;
import java.util.Date;

public class DateUtils {

	
	/**
	 * Converte uma data do tipo LocalDate e retorna uma data do tipo Date
	 * @param LocalDate
	 * @return Date
	 */
	public static Date convertLocalDateToDate(LocalDate localDate) {
		return java.sql.Date.valueOf(localDate);
	}
	
	/**
	 * Retrocede a data com base no parametro
	 * exemplo: parametro 2, data atual 17/10/2018, retorna 17/10/2016 
	 * @param int
	 * @return LocalDate
	 */
	public static LocalDate returnLastYear(int valor) {		
		LocalDate hoje = LocalDate.now();
		return hoje.minusYears(valor);
	}
	
	/**
	 * Adiciona dia e mês ao ano atual
	 * @param int mês
	 * @param int dia
	 * @return LocalDate
	 */
	public static LocalDate addMonthAndDayToDate(int month, int day) {
		LocalDate hoje = LocalDate.now();
		return LocalDate.of(hoje.getYear(), month, day);
		
	}
	
	/**
	 * Adiciona dia na data atual
	 * @param int dia
	 * @return LocalDate
	 */
	public static LocalDate addDayToDate(int day) {
		LocalDate hoje = LocalDate.now();
		return LocalDate.of(hoje.getYear(), hoje.getMonthValue(), day);		
	}
}
