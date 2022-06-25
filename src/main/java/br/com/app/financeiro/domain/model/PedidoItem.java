package br.com.app.financeiro.domain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.app.estoque.domain.model.Item;

@Entity
@Table(name = "pedido_item", schema = "almoxarifado")
public class PedidoItem extends Item {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_pedido_compra")
	private PedidoCompra pedidoCompra;

	public PedidoCompra getPedidoCompra() {
		return pedidoCompra;
	}

	public void setPedidoCompra(PedidoCompra pedidoCompra) {
		this.pedidoCompra = pedidoCompra;
	}

}
