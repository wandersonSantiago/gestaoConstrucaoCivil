package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Produto;
import br.com.system.gestaoConstrucaoCivil.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {


	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> buscarTodos() {
	 		
	 		return produtoRepository.findAll();
	 	}
}
