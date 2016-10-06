package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ItemPedido extends Item{

		
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
