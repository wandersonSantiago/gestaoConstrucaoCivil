package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.ItemRequisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoCasaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoCasaService {

	@Autowired
	private RequisicaoCasaRepository requisicaoCasaRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;

	@Transactional(readOnly = false)
	public void salvarOuEditar(RequisicaoCasa requisicaoCasa) {
		
		requisicaoCasaRepository.save(requisicaoCasa);
	}
	public List<RequisicaoCasa> buscarTodos() {
		return requisicaoCasaRepository.findAll();
	}
	public RequisicaoCasa buscarPorId(Long id) {
		return requisicaoCasaRepository.findOne(id);
	}
	public void aceitarRequisicao(Integer numeroRequisicao)
	{
		Empreendimento empreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		RequisicaoCasa requisicao = requisicaoCasaRepository.findByNumeroRequisicao(numeroRequisicao);
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
		 RequisicaoCasa requisicao = requisicaoCasaRepository.findByNumeroRequisicao(numeroRequisicao);
		 requisicao.setStatusRequisicao(StatusRequisicao.RECUSADO);
	}

}
