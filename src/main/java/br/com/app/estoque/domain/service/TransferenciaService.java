package br.com.app.estoque.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.estoque.domain.enuns.StatusTransferencia;
import br.com.app.estoque.domain.model.Transferencia;
import br.com.app.estoque.domain.repository.TransferenciaRepository;
import br.com.app.infraestructure.exception.MensagemException;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;
	@Autowired
	private BaixaEstoqueService baixarEstoque;
	@Autowired
	private EntradaEstoqueService entradaEstoque;
	 
	@Transactional(readOnly = false)
	public void salvarAlterar(Transferencia transferencia) {

		transferencia.novaTransferencia();
		baixarEstoque.baixar(transferencia);
		transferenciaRepository.save(transferencia);
	}

	@Transactional(readOnly = false)
	public void aceitarTransferencia(Long numeroNota) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Transferencia transferencia = transferenciaRepository.buscarTransferenciaPorNumeroNota(numeroNota);
		
		if(!idEmpreendimento.equals(transferencia.getEmpreendimentoDestino().getId())) {
			throw new MensagemException("Somente o Empreendimento " + transferencia.getEmpreendimentoDestino().getDescricao() + " pode aceitar a transferencia" );
		}
		transferencia.setStatusTransferencia(StatusTransferencia.EFETUADO);	
		entradaEstoque.entradaEstoque(transferencia);
		transferenciaRepository.save(transferencia);
		

	}

	@Transactional(readOnly = false)
	public void rejeitarTransferencia(Long numeroNota) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Transferencia transferencia = transferenciaRepository.buscarTransferenciaPorNumeroNota(numeroNota);
		
		if(!idEmpreendimento.equals(transferencia.getEmpreendimentoDestino().getId())) {
			throw new MensagemException("Somente o Empreendimento " + transferencia.getEmpreendimentoDestino().getDescricao() + " pode rejeitar a transferencia" );
		}		
		transferencia.rejeitarTransferencia();
		CancelamentoTransferencia cancelamento = new CancelamentoTransferencia(transferencia);
		entradaEstoque.entradaEstoque(cancelamento);
		transferenciaRepository.save(transferencia);
	}

	
	public Optional<Transferencia> buscaPorId(Long id) {
		return transferenciaRepository.findById(id);
	}

	

	public Page<Transferencia> findAll(String tipo, Pageable page) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		if(tipo.equalsIgnoreCase("ENVIADOS")) {
			return	transferenciaRepository.findByNotaFiscalEmpreendimentoId(idEmpreendimento, page);
		}else {
			return transferenciaRepository.findByEmpreendimentoDestinoId(idEmpreendimento, page);
		}		
	}

	public Page<Transferencia> findByCodigo(String tipo,String descricao, Pageable page) {
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Page<Transferencia> estoques = null;
		descricao = descricao.replaceAll("[./-]", "");

		if(tipo.equalsIgnoreCase("ENVIADOS")) {
			estoques = transferenciaRepository.findByNotaFiscalNumeroAndNotaFiscalEmpreendimentoId(Long.valueOf(descricao),
					idEmpreendimento, page);
		}else {
			estoques = transferenciaRepository.findByNotaFiscalNumeroAndEmpreendimentoDestinoId(Long.valueOf(descricao),
					idEmpreendimento, page);
		}	
		
			if(estoques.getNumberOfElements() < 1) {
				throw new MensagemException("Nota fiscal não encontrada, com o código: " +  descricao);
			}
			
		return estoques;
	}
}
