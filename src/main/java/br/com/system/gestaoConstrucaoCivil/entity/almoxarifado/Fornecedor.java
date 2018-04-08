package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.DadoEmpresa;
import br.com.system.gestaoConstrucaoCivil.findControll.FornecedorFindControll;

@Entity
@SequenceGenerator(name = "fornecedor_id_seq",
sequenceName = "fornecedor_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "fornecedor", schema = "almoxarifado")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_id_seq")
	private Long id;
	
	@JsonView(FornecedorFindControll.class)
	@Column(nullable = false)
	private boolean ativo;
	@JsonView(FornecedorFindControll.class)
	@Column(nullable = false, length = 50)
	private String contato;
	@JsonView(FornecedorFindControll.class)
	@Column(nullable = true, length = 50)
	private String observacao;

	@JsonView(FornecedorFindControll.class)
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadoEmpresa;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
    
	public DadoEmpresa getDadoEmpresa() {
		return dadoEmpresa;
	}

	public void setDadoEmpresa(DadoEmpresa dadoEmpresa) {
		this.dadoEmpresa = dadoEmpresa;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/*public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}*/

}
