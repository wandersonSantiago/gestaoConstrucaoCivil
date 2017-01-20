package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.PedidoCompra;

public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long>{

	
	@Query("From PedidoCompra p where p.empreendimento.id = ?1")
	public Collection<PedidoCompra> buscaTodos(Long idEmpreendimento);
}
