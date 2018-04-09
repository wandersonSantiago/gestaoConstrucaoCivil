package br.com.app.service.interfaceservice;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

public interface Servico<E> {

	@Transactional(readOnly = false)
	public void salvarOuEditar(E entity);
	
    public List<E> buscarTodos();
   
    public Optional<E> findById(Long id);
}
