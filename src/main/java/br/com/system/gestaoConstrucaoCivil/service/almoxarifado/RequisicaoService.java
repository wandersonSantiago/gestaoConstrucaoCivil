package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Requisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View.Summary;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoService {

	@Autowired
	private RequisicaoRepository requisicaoRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;
	
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Requisicao requisicao)
	{
		adicionarItemRequisicao(requisicao);
		Empreendimento empreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
	    requisicao.setEmpreendimento(empreendimento);
	    requisicaoRepository.save(requisicao);
	}
	public void adicionarItemRequisicao(Requisicao requisicao)
	{
		requisicao.getItem().forEach(item -> item.setRequisicao(requisicao));
	}
	@JsonView(Summary.class)
	public List<Requisicao> buscarTodos()
	{
		return requisicaoRepository.findAll();
	}
	public Requisicao buscarPorId(Long id)
    {
    	return requisicaoRepository.findOne(id);
    }
	public void aceitarRequisicao(Integer numeroRequisicao)
	{
		Empreendimento empreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		Requisicao requisicao = requisicaoRepository.findByNumeroRequisicao(numeroRequisicao);
		if(requisicao.getStatusRequisicao() == StatusRequisicao.PENDENTE)
		{
			for(RequisicaoItem item: requisicao.getItem())
			{
			    EntradaOuBaixa baixa = new EntradaOuBaixa(item.getProduto(), item.getQuantidade(), empreendimento);
			
			    estoque.baixar(baixa);
				
			}
		   requisicao.setStatusRequisicao(StatusRequisicao.EFETUADO);	
		}
		
	}
 
	public void rejeitarRequisicao(Integer numeroRequisicao)
	{
		Empreendimento empreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		Requisicao requisicao = requisicaoRepository.findByNumeroRequisicao(numeroRequisicao);
		requisicao.setStatusRequisicao(StatusRequisicao.RECUSADO);
}
}
