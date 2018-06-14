package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Categoria;
import br.com.app.exceptions.NotFoundException;
import br.com.app.repository.CategoriaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Categoria categoria){
		if(categoria.getId() == null) {
			categoria.setAtivo(true);
		}
		categoriaRepository.save(categoria);
	}
	
	
	public Optional<Categoria> findById(Long id) {
		return categoriaRepository.findById(id);
	}

	public Page<Categoria> findByDepartamentos(Pageable page) {
		return categoriaRepository.findByCategoriaIsNull(page);
	}

	public Page<Categoria> findAllByCategoria(Pageable page) {
		return categoriaRepository.findByCategoriaNotNull(page);
	}
	
	public Page<Categoria> findByDescricaoIgnoreCaseAndCategoriaIsNull(String descricao, Pageable page) {
		
		Page<Categoria> categorias = categoriaRepository.findByDescricaoContainingIgnoreCaseAndCategoriaIsNull(descricao, page);

		if (categorias == null || categorias.getNumberOfElements() < 1) {
			throw new NotFoundException(
					"Não foi possivel encontrar nenhuma categorias com esta descrição: " + descricao);
		}
		return categorias;
	}
	
	public Page<Categoria> findByDescricaoIgnoreCaseAndCategoriaNotNull(String descricao, Pageable page) {
			
			Page<Categoria> categorias = categoriaRepository.findByDescricaoContainingIgnoreCaseAndCategoriaNotNull(descricao, page);
	
			if (categorias == null || categorias.getNumberOfElements() < 1) {
				throw new NotFoundException(
						"Não foi possivel encontrar nenhuma categorias com esta descrição: " + descricao);
			}
			return categorias;
		}


	public List<Categoria> findByCategoriaId(Long id) {
		return categoriaRepository.findByCategoriaId(id);
	}


	

	
}
