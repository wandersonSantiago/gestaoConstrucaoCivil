package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.enuns.TipoModulo;
import lombok.Data;

@Entity
@SequenceGenerator(name = "permissao_id_seq", sequenceName = "permissao_id_seq",allocationSize = 1,schema="communs")
@Data
@Table(name = "permissao" , schema = "communs")
public class Permissao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissao_id_seq")
	private Long id;
	
	private String descricao;
	
	private String rol;
	
	@Enumerated(EnumType.STRING)
	private TipoModulo tipoModulo;
	
	
	
}
