package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.InformacaoRequisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoEdificioService {

	@Autowired
	private RequisicaoEdificioRepository requisicaoRepository;
	@Autowired
	private RequisicaoService requisicaoService; 
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(RequisicaoEdificio requisicaoEdificio){
		
		InformacaoRequisicao informacaoRequisicao = new InformacaoRequisicao();
		informacaoRequisicao.novaRequisicao();
		requisicaoEdificio.setInformacaoRequisicao(informacaoRequisicao);
		requisicaoRepository.save(requisicaoEdificio);
	}
	public Collection<RequisicaoEdificio> buscarTodos(){
		return requisicaoRepository.findAll();
	}
	
	public RequisicaoEdificio buscarPorId(Long id){
		return requisicaoRepository.findOne(id);
	}
	@Transactional(readOnly = false)
	public void aceitar(Integer numeroRequisicao)
	{
		RequisicaoEdificio requisicao = requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao);
	    requisicaoService.aceitar(requisicao);
	}
	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao)
	{
		RequisicaoEdificio requisicao = requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao);
		requisicaoService.rejeitar(requisicao);
	}
	
	
}
