package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;
import br.com.system.gestaoConstrucaoCivil.util.geradorCodigo.GereCodigoPacoteServico;


@Entity
@Table(name = "pacote_servico" , schema="servicos")
public class PacoteServico extends AbstractPersistable<Long>{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private boolean ativo;
	
	@Column(nullable = false)
	private Integer codigo;
	
	@Column(nullable = false,length = 50)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;
	@Column(nullable = false,length = 250)
	private String servicosAtribuidos;
	
	@Column(nullable = false,length = 250)
	private String criterioServico;
	
	@Column(nullable = false)
	private Double valor;
	
	
	public void novoPacote()
	{
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
