package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "endereco_id_seq", sequenceName = "endereco_id_seq",allocationSize = 1,schema="communs")
@Table(name = "endereco" , schema = "communs")
public class Endereco implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
	private Long id;
	
	@Column(nullable = false,length = 50)
	private String logradouro;
	@Column(nullable = false,length = 50)
	private String bairro;
	@Column(nullable = false,length = 50)
	private String localidade;
	@Column(nullable = false)
	private String numero;
	@Column(nullable = false)
	private String uf;
	@Column(nullable = false,length = 9)
	private String cep;
	@Column(nullable = true,length = 50)
	private String complemento;
	
}
