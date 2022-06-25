package br.com.app.commons.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "fabricante_id_seq", sequenceName = "fabricante_id_seq",allocationSize = 1, schema = "almoxarifado")
@Table(name = "fabricante", schema = "almoxarifado")
public class Fabricante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fabricante_id_seq")
	private Long id;
	
	@Column(length = 50)
	private String contato;
	@Column(length = 50)
	private String observacao;
	
	@OneToOne
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadoEmpresa;
}
