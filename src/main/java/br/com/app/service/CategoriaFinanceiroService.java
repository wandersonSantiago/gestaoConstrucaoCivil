package br.com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.CategoriaFinanceiro;
import br.com.app.enuns.CategoriaEnum;
import br.com.app.exceptions.NotFoundException;
import br.com.app.repository.CategoriaFinanceiroRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CategoriaFinanceiroService {

	@Autowired
	private CategoriaFinanceiroRepository categoriaRepository;
	
	@Transactional(readOnly = false)
	public void insert(CategoriaFinanceiro categoria){
		categoria.setAtivo(true);
		if(categoriaRepository.existsByDescricao(categoria.getDescricao())){
			throw new  NotFoundException("ja existe uma categoria cadastrada com este nome");
		}
		categoriaRepository.save(categoria);
	}
	
	@Transactional(readOnly = false)
	public void update(CategoriaFinanceiro categoria){
		CategoriaFinanceiro  cat = categoriaRepository.findByDescricao(categoria.getDescricao());		
		if(!cat.getId().equals(categoria.getId())){
				throw new  NotFoundException("ja existe uma categoria cadastrada com este nome");
		}		
		categoriaRepository.save(categoria);
	}
	
	
	public CategoriaFinanceiro findById(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Nenhuma categoria foi encontrado!"));
	}

	public Page<CategoriaFinanceiro> findByAll(Pageable page) {
		return categoriaRepository.findAll(page);
	}

	public Page<CategoriaFinanceiro> findAllByCategoriaFinanceiro(Pageable page) {
		return categoriaRepository.findByCategoriaNotNull(page);
	}
	
	public Page<CategoriaFinanceiro> findByDescricaoIgnoreCaseAndCategoriaFinanceiroIsNull(String descricao, Pageable page) {
		
		Page<CategoriaFinanceiro> categorias = categoriaRepository.findByDescricaoContainingIgnoreCaseAndCategoriaIsNull(descricao, page);

		if (categorias == null || categorias.getNumberOfElements() < 1) {
			throw new NotFoundException(
					"Não foi possivel encontrar nenhuma categorias com esta descrição: " + descricao);
		}
		return categorias;
	}
	
	public Page<CategoriaFinanceiro> findByDescricaoIgnoreCaseAndCategoriaFinanceiroNotNull(String descricao, Pageable page) {
			
			Page<CategoriaFinanceiro> categorias = categoriaRepository.findByDescricaoContainingIgnoreCaseAndCategoriaNotNull(descricao, page);
	
			if (categorias == null || categorias.getNumberOfElements() < 1) {
				throw new NotFoundException(
						"Não foi possivel encontrar nenhuma categorias com esta descrição: " + descricao);
			}
			return categorias;
		}


	public List<CategoriaFinanceiro> findByCategoriaFinanceiroId(Long id) {
		return categoriaRepository.findByCategoriaId(id);
	}


	public List<CategoriaFinanceiro> findByCategorias(CategoriaEnum catEnum) {
		return categoriaRepository.findByTipoAndCategoriaIsNull(catEnum);
	}


	public Page<CategoriaFinanceiro> findByDescricaoIgnoreCase(String descricao, PageRequest page) {
		Page<CategoriaFinanceiro> categorias = categoriaRepository.findByDescricaoContainingIgnoreCase(descricao, page);
		
		if (categorias == null || categorias.getNumberOfElements() < 1) {
			throw new NotFoundException(
					"Não foi possivel encontrar nenhuma categorias com esta descrição: " + descricao);
		}
		return categorias;
	}


	

	
}
