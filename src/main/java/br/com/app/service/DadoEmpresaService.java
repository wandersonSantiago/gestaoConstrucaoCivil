package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.DadoEmpresa;
import br.com.app.repository.DadoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DadoEmpresaService {

	@Autowired
	private DadoEmpresaRepository dadoEmpresaRepository;

	@Transactional(readOnly = false)
	public DadoEmpresa insert(DadoEmpresa empresa) {
		return dadoEmpresaRepository.save(empresa);
	}

	public boolean existeCnpjCadastrado(String cnpj) {
		return dadoEmpresaRepository.existeCnpj(cnpj);
	}

	public boolean existeIeCadastrado(String ie) {
		return dadoEmpresaRepository.existeIe(ie);
	}

	public DadoEmpresa findByCnpj(String cnpj) {
		 return dadoEmpresaRepository.findByCnpj(cnpj);
	
	}

}
