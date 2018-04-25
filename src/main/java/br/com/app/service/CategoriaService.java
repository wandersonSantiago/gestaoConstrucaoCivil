package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Categoria;
import br.com.app.repository.CategoriaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CategoriaService {

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
	 
	
	public Optional<Categoria> findById(Long id) {
		return categoriaRepository.findById(id);
	}
}
