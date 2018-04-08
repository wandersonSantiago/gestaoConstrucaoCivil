package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;
import br.com.system.gestaoConstrucaoCivil.repository.CategoriaRepository;
import br.com.system.gestaoConstrucaoCivil.service.interfaceservice.Servico;

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
	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findOne(id);
	}
}
