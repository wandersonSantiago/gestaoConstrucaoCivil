package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.ItemRequisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoEdificioService {

	@Autowired
	private RequisicaoEdificioRepository requisicaoEdificioRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;

	@Transactional(readOnly = false)
	public void salvarOuEditar(RequisicaoEdificio requisicaoEdificio) {

		
		requisicaoEdificioRepository.save(requisicaoEdificio);
		
	}
    public Collection<RequisicaoEdificio> buscarTodos() {
		return requisicaoEdificioRepository.findAll();
	}

	public RequisicaoEdificio buscarPorId(Long id) {
		return requisicaoEdificioRepository.findOne(id);
	}
	
	public void aceitarRequisicao(Integer numeroRequisicao)
	{
		Empreendimento empreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		RequisicaoEdificio requisicao = requisicaoEdificioRepository.findByNumeroRequisicao(numeroRequisicao);
		if(requisicao.getStatusRequisicao() == StatusRequisicao.PENDENTE)
		{
			for(ItemRequisicao item: requisicao.getItem())
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
		RequisicaoEdificio requisicao = requisicaoEdificioRepository.findByNumeroRequisicao(numeroRequisicao);
		requisicao.setStatusRequisicao(StatusRequisicao.RECUSADO);
	}
}
