package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.PedidoCompra;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.PedidoCompraRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PedidoCompraService {

	
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;
	
	@Transactional(readOnly = false)
	public void salvaAltera(PedidoCompra pedidoCompra){
		pedidoCompraRepository.save(pedidoCompra);
	}
	
	
	public Collection<PedidoCompra> buscarTodos(){
		return pedidoCompraRepository.findAll();
	}
	
	public PedidoCompra buscaPorId(Long id){
		return pedidoCompraRepository.findOne(id);
	}
}
