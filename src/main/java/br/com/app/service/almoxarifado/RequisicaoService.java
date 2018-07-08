package br.com.app.service.almoxarifado;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Usuario;
import br.com.app.entity.almoxarifado.Requisicao;
import br.com.app.enuns.StatusRequisicao;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.MensagemException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.RequisicaoRepository;
import br.com.app.util.gerador.codigo.GeraNumeroRequisicao;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoService {

	@Autowired
	private RequisicaoRepository requisicaoRepository;
	@Autowired
	private BaixaEstoqueService baixaEstoque;

	@Transactional(readOnly = false)
	public void aceitar(Long  idRequisicao) {
		
		Requisicao requisicao = findByIdAndEmpreendimentoId(idRequisicao);
		if (requisicao.getStatusRequisicao() == StatusRequisicao.PENDENTE) {
			baixaEstoque.baixar(requisicao);
			requisicao.setStatusRequisicao(StatusRequisicao.EFETUADO);
			requisicao.setDataSaida(new Date());
			requisicaoRepository.save(requisicao);
		}

	}

	@Transactional(readOnly = false)
	public void rejeitar(Long idRequisicao) {
		Requisicao requisicao = findByIdAndEmpreendimentoId(idRequisicao);
		requisicao.setStatusRequisicao(StatusRequisicao.RECUSADO);
		requisicaoRepository.save(requisicao);
	}

	@Transactional(readOnly = false)
	public void insert(Requisicao requisicao) {
		requisicao.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		requisicao.setStatusRequisicao(StatusRequisicao.PENDENTE);
		GeraNumeroRequisicao gerarNumero = new GeraNumeroRequisicao();
		requisicao.setNumeroRequisicao(gerarNumero.gerarNumeroRequisicao());
		requisicao.setDataCriacao(new Date());
		
		requisicaoRepository.save(requisicao);

	}
	public Requisicao findByIdAndEmpreendimentoId(Long id) {
		Usuario usuario = SessionUsuario.getInstance().getUsuario();
		Requisicao requisicao = requisicaoRepository.findByIdAndEmpreendimentoId(id, usuario.getEmpreendimento().getId());
		if(requisicao == null) {
			throw new NotFoundException("Requisição não encontrada");
		}
		return requisicao;
	}

	public Page<Requisicao> findAll(String tipo, PageRequest page) {
		return requisicaoRepository.findAll(page);
	}
	
	public Page<Requisicao> findByCodigo(String tipo,String descricao, Pageable page) {
		Usuario usuario = SessionUsuario.getInstance().getUsuario();
		Page<Requisicao> requisicao = null;
		if (!descricao.matches("[0-9]+")) {
			throw new MensagemException("Código inválido: " +  descricao);
		}
		descricao = descricao.replaceAll("[./-]", "");

		if(tipo.equalsIgnoreCase("MINHA_REQUISICAO")) {
			requisicao = requisicaoRepository.findByNumeroRequisicaoAndEmpreendimentoIdAndIdUsuario(new Integer(descricao),
					usuario.getEmpreendimento().getId(), usuario.getId(), page);
		}else {
			requisicao = requisicaoRepository.findByNumeroRequisicaoAndEmpreendimentoId(new Integer(descricao),
					usuario.getEmpreendimento().getId(), page);
		}	
		
			if(requisicao.getNumberOfElements() < 1) {
				throw new MensagemException("Requisição não encontrada, com o código: " +  descricao);
			}
			
		return requisicao;
	}

}
