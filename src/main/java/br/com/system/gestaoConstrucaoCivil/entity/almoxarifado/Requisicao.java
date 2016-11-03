package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.time.LocalDate;

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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;

@Entity
@SequenceGenerator(name = "baixa_estoque_id_seq",
sequenceName = "baixa_estoque_id_seq",
initialValue = 1,
allocationSize = 50)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "baixa_estoque")
public class Requisicao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "baixa_estoque_id_seq")
	protected Long id;
	
	
	@Column(name ="quantidade_saida", nullable = false)
	protected Integer quantidadeSaida;
	@Column(name ="data_saida", nullable = true)
	protected LocalDate dataSaida;
	
	@ManyToOne
	@JoinColumn(name="id_produto",nullable = true)
	protected Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_area_produto")
	protected AreaProduto areaProduto;
	
	@Enumerated(EnumType.STRING)
	protected StatusRequisicao statusRequisicao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		public Integer getQuantidadeSaida() {
		return quantidadeSaida;
	}
	public void setQuantidadeSaida(Integer quantidadeSaida) {
		this.quantidadeSaida = quantidadeSaida;
	}
	public LocalDate getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public AreaProduto getAreaProduto() {
		return areaProduto;
	}
	public void setAreaProduto(AreaProduto areaProduto) {
		this.areaProduto = areaProduto;
	}
	public StatusRequisicao getStatusRequisicao() {
		return statusRequisicao;
	}
	public void setStatusRequisicao(StatusRequisicao statusRequisicao) {
		this.statusRequisicao = statusRequisicao;
	}
        
}
