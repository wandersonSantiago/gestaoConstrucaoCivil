package br.com.app.entity.servicos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.entity.DadoEmpresa;

@Entity
@SequenceGenerator(name = "prestadora_servico_id_seq", sequenceName = "prestadora_servico_id_seq", schema = "servicos")
@Table(name = "prestadora_servico", schema = "servicos")
public class PrestadoraServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestadora_servico_id_seq")
	private Long id;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadoEmpresa;

	@OneToMany(mappedBy = "prestadoraServico", cascade = CascadeType.ALL)
	private List<ServicoEmpresa> servicos;

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

	public List<ServicoEmpresa> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoEmpresa> servicos) {
		this.servicos = servicos;
	}

	

}
