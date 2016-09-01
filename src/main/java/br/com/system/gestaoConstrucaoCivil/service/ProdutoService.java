package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Produto;
import br.com.system.gestaoConstrucaoCivil.repository.ProdutoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ProdutoService {


	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> buscarTodos() {
	 		
	 		return produtoRepository.findAll();
	 }
	
	public Produto buscaProdutoPorId(Long id){
		
		return produtoRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Produto produto)
	{
		produtoRepository.save(produto);
	
	}
	
	
}
