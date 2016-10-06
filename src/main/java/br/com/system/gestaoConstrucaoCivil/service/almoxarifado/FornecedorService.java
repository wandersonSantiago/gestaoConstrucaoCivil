package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Fornecedor;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.FornecedorRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> buscarTodos() {

		return fornecedorRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void salvarOuEditar(Fornecedor fornecedor)
	{
		fornecedorRepository.save(fornecedor);
	}
	public Fornecedor buscarPorId(Long id) {
		
		return fornecedorRepository.findOne(id);
	}
}
