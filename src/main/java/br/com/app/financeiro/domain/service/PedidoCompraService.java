package br.com.app.financeiro.domain.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.financeiro.domain.model.PedidoCompra;
import br.com.app.financeiro.domain.repository.PedidoCompraRepository;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PedidoCompraService {

	
	@Autowired
	private PedidoCompraRepository pedidoCompraRepository;
	
	@Transactional(readOnly = false)
	public void salvaAltera(PedidoCompra pedidoCompra){
		salvaAtributosPedidoCompra(pedidoCompra);
		pedidoCompraRepository.save(pedidoCompra);
	}
	
	
	public Collection<PedidoCompra> buscarTodos(){
		return pedidoCompraRepository.buscaTodos(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId());
	}
	
	public Optional<PedidoCompra> buscaPorId(Long id){
		return pedidoCompraRepository.findById(id);
	}
	
	public PedidoCompra salvaAtributosPedidoCompra(PedidoCompra pedidoCompra){
		
		pedidoCompra.setDataCadastro(new Date());
		pedidoCompra.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		pedidoCompra.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		for(int i = 0 ; i < pedidoCompra.getItens().size() ; i++){
			pedidoCompra.getItens().get(i).setPedidoCompra(pedidoCompra);
		}
		return pedidoCompra;
	}
}
