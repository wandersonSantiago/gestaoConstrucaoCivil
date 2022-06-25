package br.com.app.servico.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.app.commons.domain.model.DadoEmpresa;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "prestadora_servico_id_seq", sequenceName = "prestadora_servico_id_seq", allocationSize = 1, schema = "servicos")
@Table(name = "prestadora_servico", schema = "servicos")
public class PrestadoraServico implements Serializable{

	 
	private static final long serialVersionUID = -4568451591344862134L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestadora_servico_id_seq")
	private Long id;

	@Column(length = 50)
	private String contato;
	@Column(length = 50)
	private String observacao;
	
	@OneToOne
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadoEmpresa;

	@JsonIgnore
	@OneToMany(mappedBy = "prestadoraServico", cascade = CascadeType.ALL)
	private List<ServicoEmpresa> servicos;

}
