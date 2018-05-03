package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Categoria;
import br.com.app.entity.Estrutura;
import br.com.app.exceptions.NotFoundException;
import br.com.app.repository.CategoriaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Categoria categoria){
		categoriaRepository.save(categoria);
	}
	
	public List<Categoria> buscarTodos() {		
		return categoriaRepository.findAll();
	} 
	
	public Optional<Categoria> findById(Long id) {
		return categoriaRepository.findById(id);
	}

	public Page<Categoria> findByDepartamentos(Pageable page) {
		return categoriaRepository.findByCategoriaIsNull(page);
	}

	public Page<Categoria> findByDescricaoIgnoreCaseAndCategoriaIsNull(String descricao, Pageable page) {
		
		Page<Categoria> categorias = categoriaRepository.findByDescricaoContainingIgnoreCaseAndCategoriaIsNull(descricao, page);

		if (categorias == null || categorias.getNumberOfElements() < 1) {
			throw new NotFoundException(
					"Não foi possivel encontrar nenhuma categorias com esta descrição: " + descricao);
		}
		return categorias;
	}
}
