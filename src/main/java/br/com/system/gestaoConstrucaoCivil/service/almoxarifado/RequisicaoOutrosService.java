package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.InformacaoRequisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoOutrosRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoOutrosService {

	@Autowired
	private RequisicaoOutrosRepository requisicaoRepository;
	@Autowired
	private RequisicaoService requisicaoService; 
	

	@Transactional(readOnly = false)
	public void salvarOuEditar(RequisicaoOutros requisicaoOutros){
		
		InformacaoRequisicao informacaoRequisicao = new InformacaoRequisicao();
		informacaoRequisicao.novaRequisicao();
		requisicaoOutros.setInformacaoRequisicao(informacaoRequisicao);
		requisicaoRepository.save(requisicaoOutros);
	}
	public Collection<RequisicaoOutros> buscarTodos(){
		return requisicaoRepository.findAll();
	}
	
	public RequisicaoOutros buscaPorId(Long id){
		return requisicaoRepository.findOne(id);
	}
	@Transactional(readOnly = false)
	public void aceitar(Integer numeroRequisicao)
	{
		RequisicaoOutros requisicao = requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao);
	    requisicaoService.aceitar(requisicao);
	}
	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao)
	{
		RequisicaoOutros requisicao = requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao);
		requisicaoService.rejeitar(requisicao);
	}
	
}
