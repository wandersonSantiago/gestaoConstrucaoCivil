package br.com.app.entity.almoxarifado;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Usuario;

@Entity
@SequenceGenerator(name = "pedido_compra_id_seq",
sequenceName = "pedido_compra_id_seq",schema="almoxarifado")
@Table(name = "pedido_compra" , schema="almoxarifado")
public class PedidoCompra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pedido_compra_id_seq")
	private Long id;
	
	@OneToMany(mappedBy = "pedidoCompra", cascade = CascadeType.ALL)
	private List<PedidoItem> itens;
	@ManyToOne
	private Fornecedor fornecedor;
    
	@Column(name = "numeroPedido")
	private Integer numeroPedido;
	
	@ManyToOne
	private Empreendimento empreendimento;
	
	@Temporal(TemporalType.DATE)
	private Date previsao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name="usuario_cadastro")
	private Usuario usuarioCadastro;
			
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}
    
	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoCompra other = (PedidoCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
