package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Categoria;
import br.com.app.repository.CategoriaRepository;
import br.com.app.service.interfaceservice.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CategoriaService implements Servico<Categoria>{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarTodos() {
		
		return categoriaRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void salvarOuEditar(Categoria categoria)
	{
		categoriaRepository.save(categoria);
	}
	 
	@Override
	public Optional<Categoria> findById(Long id) {
		return categoriaRepository.findById(id);
	}
}
