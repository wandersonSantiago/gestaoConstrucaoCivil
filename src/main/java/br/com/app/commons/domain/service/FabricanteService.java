package br.com.app.commons.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Fabricante;
import br.com.app.commons.domain.repository.FabricanteRepository;
import br.com.app.infraestructure.exception.MensagemException;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class FabricanteService {

	@Autowired
	private FabricanteRepository  fabricanteRepository;
	
	
	@Transactional(readOnly = false)
	public void insert(Fabricante fabricante){
		fabricante.setId(null);
		if(existeCnpjCadastrado(fabricante.getDadoEmpresa().getCnpj())) {
			throw new MensagemException("CNPJ ja consta cadastrado como uma fabricante");
		}
		fabricanteRepository.save(fabricante);
	}
	@Transactional(readOnly = false)
	public void update(Fabricante fabricante){	
		findById(fabricante.getId());
		fabricanteRepository.save(fabricante);
	}
	
	public Optional<Fabricante> findById(Long id) {		
		return fabricanteRepository.findById(id);
	}
	
	public List<Fabricante> buscarTodos() {
		return fabricanteRepository.findAll();
	}

	public Optional<Fabricante> buscarPorCNPJ(String cnpj) {		
		return fabricanteRepository.findByDadoEmpresaCnpj(cnpj);
	}
	
	public Boolean existeCnpjCadastrado(String cnpj) {
		return fabricanteRepository.existsByDadoEmpresaCnpj(cnpj);
	}

	public Page<Fabricante> findAll(Pageable page) {
		return fabricanteRepository.findAll(page);
	}

	public Page<Fabricante> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<Fabricante> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = fabricanteRepository.findByDadoEmpresaCnpjContaining(descricao, page);
		}else {
			list = fabricanteRepository.findByDadoEmpresaRazaoSocialIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}
}
