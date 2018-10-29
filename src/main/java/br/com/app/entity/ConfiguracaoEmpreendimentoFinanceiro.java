package br.com.app.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;

import br.com.app.pojo.util.DateUtils;


@Entity
public class ConfiguracaoEmpreendimentoFinanceiro extends ConfiguracaoEmpreendimento{

	private static final long serialVersionUID = 1L;
	
	private Integer dataBase = 1;
	
	
	
	
	public Date getDataBaseInicial() {
		LocalDate localDate = DateUtils.addDayToDate(dataBase);		
		return DateUtils.convertLocalDateToDate(localDate);
	}
	public Date getDataBaseFinal() {
		dataBase = dataBase == 0 ? 28 : dataBase -1;		
		LocalDate localDate = DateUtils.addDayToDate(dataBase);		
		return DateUtils.convertLocalDateToDate(localDate);
	}
}
