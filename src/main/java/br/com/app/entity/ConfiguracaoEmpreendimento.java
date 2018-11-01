package br.com.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.enuns.DataBaseEnum;
import br.com.app.pojo.util.DateUtils;
import lombok.Data;

@Entity
@Table(name = "configuracao" , schema = "communs")
@SequenceGenerator(name = "configuracao_id_seq", sequenceName = "configuracao_id_seq",  allocationSize = 1,schema = "communs")
@Data
public class ConfiguracaoEmpreendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configuracao_id_seq")
	private Long id;
	
	private Integer dataBaseFinanceiro;
		
	@OneToOne
	private Empreendimento empreendimento;
	
	public Date getDataBaseInicial() {
		LocalDate localDate = DateUtils.addDayToDate(dataBaseFinanceiro);		
		return DateUtils.convertLocalDateToDate(localDate);
	}
	
	public Date getDataBaseFinal() {
		LocalDate hoje = LocalDate.now();
		int data = (dataBaseFinanceiro == 0 || dataBaseFinanceiro ==1 )  ? 31 : dataBaseFinanceiro -1;		
		LocalDate localDate = DateUtils.addMonthAndDayToDate( hoje.getMonthValue() + 1,data);		
		return DateUtils.convertLocalDateToDate(localDate);
	}
	
	public DataBaseEnum getDataBaseFinanceiro() {
		return DataBaseEnum.getEnum(dataBaseFinanceiro);
	}
	
	public void setDataBaseFinanceiro(DataBaseEnum dataBase) {
		this.dataBaseFinanceiro= dataBase.getId();
	}
	
	
}
