package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View.Summary;
import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;

@Entity

@NamedEntityGraph(name = "Produto.detail",
attributeNodes = {@NamedAttributeNode("categoria"),@NamedAttributeNode("fabricante"),@NamedAttributeNode("tipoProduto")})

@Table(name = "produto" , schema="almoxarifado")
public class Produto extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@JsonView(Summary.class)
	@Column(nullable = true,unique = true)
	private Integer codigo;
	
	@JsonView(Summary.class)
	@Column(nullable = false, length = 50,unique = true)
	private String codigoBarra;

	@JsonView(Summary.class)
	@Column(nullable = false, length = 50)
	private String descricao;
	@Column(nullable = true)
	private boolean ativo;
	@Enumerated(EnumType.STRING)
	private UnidadeMedidaEnum unidadeMedida;
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produtos_fornecedores", joinColumns = @JoinColumn(name = "id_produto"), 
	inverseJoinColumns = @JoinColumn(name = "id_fornecedor"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Fornecedor> fornecedores;
	
	@ManyToOne
    @JoinColumn(name="id_fabricante",nullable = true)
	private Fabricante fabricante;
	
	@ManyToOne
    @JoinColumn(name="id_tipo",nullable = true)
	private TipoProduto tipoProduto;
	
	@Transient
	private boolean geraCodigoBarra = true;
	
	@ManyToOne
	@JoinColumn(name ="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	public boolean isGeraCodigoBarra() {
		return geraCodigoBarra;
	}
    public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
    

	public UnidadeMedidaEnum getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public void setUsuarioCadastrado()
	{
		this.usuarioCadastro = SessionUsuario.getInstance().getUsuario();
	}
			
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
    


}