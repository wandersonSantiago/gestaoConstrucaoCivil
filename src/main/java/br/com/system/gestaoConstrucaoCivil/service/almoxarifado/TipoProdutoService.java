package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.TipoProduto;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.TipoProdutoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TipoProdutoService {

	
	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;
	
	@Transactional(readOnly = false)
	public void salvaAltera(TipoProduto tipoProduto){
		
		tipoProdutoRepository.save(tipoProduto);
	}
	
	public List<TipoProduto> buscarTodos(){
		
		return tipoProdutoRepository.findAll();
	}
	
	public Optional<TipoProduto> buscaPorId(Long id){
		
		return tipoProdutoRepository.findById(id);
	}
	
}
