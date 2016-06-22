package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;
import br.com.system.gestaoConstrucaoCivil.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarTodos() {
		
		return categoriaRepository.findAll();
	}
}
