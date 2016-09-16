package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "baixa_estoque_id_seq",
sequenceName = "baixa_estoque_id_seq",
initialValue = 1,
allocationSize = 50)

@Table(name = "baixa_estoque")
public class BaixaEstoque implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "baixa_estoque_id_seq")
	Long id;
	
	@Column(name ="andar", nullable = false)
	private Integer andar;
	@Column(name ="torre", nullable = false)
	private Integer torre;
	@Column(name ="apartamento", nullable = true)
	private Integer apartamento;
	@Column(name ="Numero_casa", nullable = true)
	private Integer NumeroCasa;
	@Column(name ="quantidade_saida", nullable = false)
	private Integer quantidadeSaida;
	@Column(name ="data_saida", nullable = true)
	private LocalDate dataSaida;
	
	@ManyToOne
	@JoinColumn(name="id_produto",nullable = true)
	private Produto produto;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAndar() {
		return andar;
	}
	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	public Integer getTorre() {
		return torre;
	}
	public void setTorre(Integer torre) {
		this.torre = torre;
	}
	public Integer getApartamento() {
		return apartamento;
	}
	public void setApartamento(Integer apartamento) {
		this.apartamento = apartamento;
	}
	public Integer getNumeroCasa() {
		return NumeroCasa;
	}
	public void setNumeroCasa(Integer numeroCasa) {
		NumeroCasa = numeroCasa;
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
       
}
