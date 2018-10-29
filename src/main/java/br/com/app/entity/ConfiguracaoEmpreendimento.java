package br.com.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.enuns.StatusEmpreendimento;
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

	
	
}
