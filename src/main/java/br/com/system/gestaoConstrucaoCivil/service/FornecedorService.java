package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Fornecedor;
import br.com.system.gestaoConstrucaoCivil.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> buscarTodos() {

		return fornecedorRepository.findAll();
	}
}
