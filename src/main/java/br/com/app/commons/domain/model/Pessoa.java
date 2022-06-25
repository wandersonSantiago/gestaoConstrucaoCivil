package br.com.app.commons.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.commons.domain.enuns.EstadoCivilEnum;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "pessoa_id_seq", sequenceName = "pessoa_id_seq",allocationSize = 1,schema="communs")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa" , schema = "communs")
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = -9083483781974164629L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
	private Long id;
	
	@Column(nullable = false,length = 50)
	protected String nomeCompleto;
	@Column(nullable = false)
	protected Integer idade;
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_endereco",nullable = false)
	protected Endereco endereco;
	
	@Column(nullable = false,length = 20,unique = true)
	protected String rg;
	@Column(nullable = false,length = 20,unique = true)
	protected String cpf;
	@Column(nullable = false,length = 15)
	protected String telefoneFixo;
	@Column(nullable = false,length = 15)
	protected String telefoneCelular;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	protected Date dataNascimento;
	@Column(nullable = false)
	protected String sexo;
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	protected EstadoCivilEnum estadoCivil;
	
}
