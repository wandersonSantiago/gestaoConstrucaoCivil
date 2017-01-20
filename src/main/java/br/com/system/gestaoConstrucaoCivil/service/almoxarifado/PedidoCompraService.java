package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.PedidoCompra;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.PedidoCompraRepository;

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
	
	public PedidoCompra buscaPorId(Long id){
		return pedidoCompraRepository.findOne(id);
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
