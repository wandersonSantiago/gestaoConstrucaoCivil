package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.ItemTransferencia;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.enuns.Situacao;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusTransferencia;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.TransferenciaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoqueService;
	
	
	@Transactional(readOnly = false)
	public void salvaAltera(Transferencia transferencia) {

		transferencia.setStatusTransferencia(StatusTransferencia.PENDENTE);
		transferencia.getNotaFiscal().setSituacao(Situacao.OK);
		transferencia.getNotaFiscal().setTipoNota(TipoNotaEnum.TRANSFERENCIA_ESTOQUE_EMPREENDIMENTO);
		transferencia.getNotaFiscal().setDataNota(new Date());
		Empreendimento empreendimentoSaida = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		transferencia.getNotaFiscal().setEmpreendimento(empreendimentoSaida);
		adicionarTransferenciaItem(transferencia);

		for (ItemTransferencia item : transferencia.getItens()) {
			EntradaOuBaixa baixa = new EntradaOuBaixa(item.getProduto(), item.getQuantidade(),
					transferencia.getNotaFiscal().getEmpreendimento());
			estoqueService.baixar(baixa);
		}
		transferencia.setStatusTransferencia(StatusTransferencia.PENDENTE);
		transferenciaRepository.save(transferencia);
	}
	private void adicionarTransferenciaItem(Transferencia transferencia)
	{
		for(ItemTransferencia item : transferencia.getItens())
		{
			item.setTransferencia(transferencia);
		}
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
			EntradaOuBaixa entrada = new EntradaOuBaixa(item.getProduto(),item.getQuantidade(), transferencia.getNotaFiscal().getEmpreendimento());
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
