package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "empresa_contratante_id_seq", sequenceName = "empresa_contratante_id_seq", schema = "communs")
@Table(name = "empresa_contratante" , schema = "communs")
public class EmpresaContratante implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_contratante_id_seq")
	private Long id;
	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadoEmpresa;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DadoEmpresa getDadoEmpresa() {
		return dadoEmpresa;
	}
	public void setDadoEmpresa(DadoEmpresa dadoEmpresa) {
		this.dadoEmpresa = dadoEmpresa;
	}
	
}
