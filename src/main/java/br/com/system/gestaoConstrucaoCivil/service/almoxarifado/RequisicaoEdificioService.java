package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.InformacaoRequisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
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
		
		return requisicaoRepository.buscarTodasRequisicoes(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId());
	}
	
	public RequisicaoEdificio buscarPorId(Long id){
		return requisicaoRepository.findOne(id);
	}
	@Transactional(readOnly = false)
	public void aceitar(Integer numeroRequisicao)
	{
		requisicaoService.aceitar(requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao));
	}
	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao)
	{
		requisicaoService.rejeitar(requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao));
	}
	
	
}
