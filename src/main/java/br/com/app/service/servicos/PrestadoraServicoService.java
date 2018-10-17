package br.com.app.service.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

 
import br.com.app.entity.servicos.PrestadoraServico;
import br.com.app.pojo.MensagemException;
import br.com.app.repository.servicos.PrestadoraServicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PrestadoraServicoService {

	@Autowired
	private PrestadoraServicoRepository prestadoraServicoRepository;

	
	@Transactional(readOnly = false)
	public void insert(PrestadoraServico prestadoraServico) {
		prestadoraServicoRepository.save(prestadoraServico);
	}

	public PrestadoraServico findById(Long id) {

		return prestadoraServicoRepository.findById(id).get();
	}

	
	public Optional<PrestadoraServico> buscarPorCNPJ(String cnpj) {		
		return prestadoraServicoRepository.findByDadoEmpresaCnpj(cnpj);
	}
	
	public Boolean existeCnpjCadastrado(String cnpj) {
		return prestadoraServicoRepository.existsByDadoEmpresaCnpj(cnpj);
	}
	
	public Page<PrestadoraServico> findAll(Pageable page) {
		return prestadoraServicoRepository.findAll(page);
	}

	public Page<PrestadoraServico> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<PrestadoraServico> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = prestadoraServicoRepository.findByDadoEmpresaCnpjContaining(descricao, page);
		}else {
			list = prestadoraServicoRepository.findByDadoEmpresaRazaoSocialIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}
}
