package br.com.app.entity.servicos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.entity.Categoria;
import br.com.app.util.gerador.codigo.GereCodigoPacoteServico;

@Entity
@SequenceGenerator(name = "pacote_servico_id_seq", sequenceName = "pacote_servico_id_seq", schema = "servicos")
@Table(name = "pacote_servico", schema = "servicos")
public class PacoteServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pacote_servico_id_seq")
	private Long id;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false)
	private Integer codigo;

	@Column(nullable = false, length = 50)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;
	@Column(nullable = false, length = 250)
	private String servicosAtribuidos;

	@Column(nullable = false, length = 250)
	private String criterioServico;

	@Column(nullable = false)
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void novoPacote() {
		this.codigo = new GereCodigoPacoteServico().gerarNumeroPacote();

	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getCodigoPacote() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getServicosAtribuidos() {
		return servicosAtribuidos;
	}

	public void setServicosAtribuidos(String servicosAtribuidos) {
		this.servicosAtribuidos = servicosAtribuidos;
	}

	public String getCriterioServico() {
		return criterioServico;
	}

	public void setCriterioServico(String criterioServico) {
		this.criterioServico = criterioServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
