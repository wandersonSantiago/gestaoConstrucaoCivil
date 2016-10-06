package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface Servico<E> {

	@Transactional(readOnly = false)
	public void salvarOuEditar(E entity);
	
    public List<E> buscarTodos();
    public E buscarPorId(Long id);
}
