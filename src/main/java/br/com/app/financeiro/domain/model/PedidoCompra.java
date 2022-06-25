package br.com.app.financeiro.domain.model;

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

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.commons.domain.model.Fornecedor;
import br.com.app.commons.domain.model.Usuario;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "pedido_compra_id_seq", sequenceName = "pedido_compra_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "pedido_compra", schema = "almoxarifado")
public class PedidoCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_compra_id_seq")
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
	@JoinColumn(name = "usuario_cadastro")
	private Usuario usuarioCadastro;

}
