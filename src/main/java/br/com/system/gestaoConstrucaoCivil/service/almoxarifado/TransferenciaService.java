package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.ItemTransferencia;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.enuns.Situacao;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusTransferencia;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.TransferenciaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoqueService;
	
	
	@Transactional(readOnly = false)
	public void salvaAltera(Transferencia transferencia){
		
		for(ItemTransferencia item : transferencia.getItens())
		{
			EntradaOuBaixa baixa = new EntradaOuBaixa(item.getProduto(),item.getQuantidade(), transferencia.getEmpreendimentoSaida());
			estoqueService.baixar(baixa);
		}
		transferencia.setStatusTransferencia(StatusTransferencia.PENDENTE);
		transferenciaRepository.save(transferencia);		
	}
	@Transactional(readOnly = false)
	public void aceitarTransferencia(Long numeroNota)
	{
		
		Transferencia transferencia =  transferenciaRepository.buscarTransferenciaPorNumeroNota(numeroNota);
		
		for(ItemTransferencia item : transferencia.getItens())
		{
			EntradaOuBaixa entrada = new EntradaOuBaixa(item.getProduto(),item.getQuantidade(), transferencia.getEmpreendimentoDestino());
			estoqueService.entradaEstoque(entrada);	
		}
	    transferencia.setStatusTransferencia(StatusTransferencia.EFETUADO);
		
	    transferenciaRepository.save(transferencia);
	}
	@Transactional(readOnly = false)
	public void rejeitarTransferencia(Long numeroNota)
	{
		Transferencia transferencia =  transferenciaRepository.buscarTransferenciaPorNumeroNota(numeroNota);
		transferencia.setStatusTransferencia(StatusTransferencia.RECUSADO);
		transferencia.getNotaFiscal().setSituacao(Situacao.CANCELADA);
		for(ItemTransferencia item : transferencia.getItens())
		{
			EntradaOuBaixa entrada = new EntradaOuBaixa(item.getProduto(),item.getQuantidade(), transferencia.getEmpreendimentoSaida());
			estoqueService.entradaEstoque(entrada);
		}
		transferenciaRepository.save(transferencia);
	}
	
	public Collection<Transferencia> buscarTodos(){
		return transferenciaRepository.findAll();
	}

	public Transferencia buscaPorId(Long id){
		return transferenciaRepository.findOne(id);
	}
	
}
