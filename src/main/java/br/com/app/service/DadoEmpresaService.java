package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.DadoEmpresa;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.MensagemException;
import br.com.app.repository.DadoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DadoEmpresaService {

	@Autowired
	private DadoEmpresaRepository dadoEmpresaRepository;

	@Transactional(readOnly = false)
	public DadoEmpresa insert(DadoEmpresa empresa) {
		empresa.setId(null);
		return dadoEmpresaRepository.save(empresa);
	}
	
	@Transactional(readOnly = false)
	public DadoEmpresa update(DadoEmpresa empresa) {
		findById(empresa.getId());
		return dadoEmpresaRepository.save(empresa);
	}

 
	public DadoEmpresa findById(Long id) {
		return dadoEmpresaRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Empresa não encontrada"));
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

	public Page<DadoEmpresa> findAll(Pageable page) {
		return dadoEmpresaRepository.findAll(page);
	}

	public Page<DadoEmpresa> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<DadoEmpresa> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = dadoEmpresaRepository.findByCnpjContaining(descricao, page);
		}else {
			list = dadoEmpresaRepository.findByRazaoSocialIgnoreCaseContainingOrNomeFantasiaIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}

	

	
}
