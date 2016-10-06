package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;
import br.com.system.gestaoConstrucaoCivil.repository.CategoriaRepository;
import br.com.system.gestaoConstrucaoCivil.service.servicos.Servico;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CategoriaService implements Servico<Categoria>{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarTodos() {
		
		System.out.println("Chamou buscarTodos categoria");
		return categoriaRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void salvarOuEditar(Categoria categoria)
	{
		System.out.println("Chamou salvar categoria");
		categoriaRepository.save(categoria);
	}
	public Categoria buscarPorId(Long id) {
		System.out.println("Chamou buscarPorId categoria");
		return categoriaRepository.findOne(id);
	}
}
