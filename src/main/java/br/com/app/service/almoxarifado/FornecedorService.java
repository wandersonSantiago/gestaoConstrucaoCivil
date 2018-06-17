package br.com.app.service.almoxarifado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Fabricante;
import br.com.app.entity.almoxarifado.Fornecedor;
import br.com.app.pojo.MensagemException;
import br.com.app.repository.almoxarifado.FornecedorRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	
	public List<Fornecedor> buscarTodos() {
		return fornecedorRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void insert(Fornecedor fornecedor){
		fornecedor.setId(null);
		
		if(existeCnpjCadastrado(fornecedor.getDadoEmpresa().getCnpj())) {
			throw new MensagemException("CNPJ ja consta cadastrado como uma fabricante");
		}
		
		fornecedorRepository.save(fornecedor);
	}
	
	@Transactional(readOnly = false)
	public void update(Fornecedor fornecedor){
		findById(fornecedor.getId());
		fornecedorRepository.save(fornecedor);
	}
		
	private boolean existeCnpjCadastrado(String cnpj) {
		return fornecedorRepository.existsByDadoEmpresaCnpj(cnpj);
	}
	
	public Fornecedor findById(Long id) {		
		return fornecedorRepository.findById(id).orElseThrow(() -> new MensagemException("Fornecedor não encontrado!"));
	}
	
	public Optional<Fornecedor> buscarPorCNPJ(String cnpj) {		
		return fornecedorRepository.findByDadoEmpresaCnpj(cnpj);
	}
	
	public Page<Fornecedor> findAll(Pageable page) {
		return fornecedorRepository.findAll(page);
	}

	public Page<Fornecedor> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<Fornecedor> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = fornecedorRepository.findByDadoEmpresaCnpjContaining(descricao, page);
		}else {
			list = fornecedorRepository.findByDadoEmpresaRazaoSocialIgnoreCaseContainingOrDadoEmpresaNomeFantasiaIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}
}
