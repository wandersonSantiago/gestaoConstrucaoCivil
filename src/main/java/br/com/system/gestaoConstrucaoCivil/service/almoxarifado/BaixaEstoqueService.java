package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Auditoria;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.IItem;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoMovimentacaoEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.AuditoriaRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private AuditoriaRepository auditoriaRepository;
	
	
	@Transactional(readOnly = false)
	public void baixar(EntradaOuBaixa baixa) {
		Auditoria auditoria = new Auditoria();
		Collection<IItem> t = baixa.getItens();
		
		for(IItem item : t)
		{
			EstoqueEmpreendimento estoque = estoqueRepository.estoque(baixa.empreendimentoSaida().getId(),item.getProduto().getId());
			if(estoque != null)
			{
			 estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
		    
			if(estoque.isNegativo())
		    {
			   estoqueRepository.save(estoque);
			    auditoria.setDataCadastro(new Date());
				auditoria.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
				auditoria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
				auditoria.setTipoMovimentacao(TipoMovimentacaoEnum.SAIDA_ESTOQUE);
				auditoria.setProduto(item.getProduto());
				auditoria.setQuantidade(item.getQuantidade());
				auditoriaRepository.save(auditoria);
		    }else
		    {
		    	throw new EstoqueEmpreendimentoException("Estoque negativo");
		    }
			
			}else
			{
				throw new EstoqueEmpreendimentoException("Produto não encontrado no estoque do empreendimento");
				
			}
				
		}
		
	}
	
}
