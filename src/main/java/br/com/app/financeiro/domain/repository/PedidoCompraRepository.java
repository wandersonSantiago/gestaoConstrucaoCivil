package br.com.app.financeiro.domain.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.financeiro.domain.model.PedidoCompra;

public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long> {

	@Query("From PedidoCompra p where p.empreendimento.id = ?1")
	Collection<PedidoCompra> buscaTodos(Long idEmpreendimento);
}
