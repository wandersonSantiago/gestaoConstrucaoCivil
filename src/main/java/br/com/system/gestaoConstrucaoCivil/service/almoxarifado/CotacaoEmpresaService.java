package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresaItem;
import br.com.system.gestaoConstrucaoCivil.enuns.CotacaoEmpresaItemStatus;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoEmpresaItemRepository;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CotacaoEmpresaService {

	@Autowired
	private CotacaoEmpresaRepository cotacaoEmpresaRepository;
	@Autowired
	private CotacaoEmpresaItemRepository cotaEmpresaItemReposi;
	private List<CotacaoEmpresaItem> itensGanhadores = new ArrayList<CotacaoEmpresaItem>(); 

	private Integer chamada = 0;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(CotacaoEmpresa cotacaoEmpresa) {

		for (CotacaoEmpresaItem item : cotacaoEmpresa.getItens()) {
			item.setId(null);
			item.setStatus(CotacaoEmpresaItemStatus.PENDENTE);
			item.setCotacaoEmpresa(cotacaoEmpresa);
		}
		cotacaoEmpresaRepository.save(cotacaoEmpresa);
	}

	public List<CotacaoEmpresa> buscarTodos() {

		return cotacaoEmpresaRepository.findAll();
	}

	public CotacaoEmpresa buscarPorId(Long id) {

		return cotacaoEmpresaRepository.findOne(id);
	}
	public List<CotacaoEmpresa> ganhadores(Long idCotacao)
	{
		return cotacaoEmpresaRepository.buscarGanhadores(idCotacao);
	}
	
	@Transactional(readOnly = false)
	public void verificarGanhadores(Cotacao cotacao) {
		List<CotacaoEmpresaItem> itens = cotaEmpresaItemReposi.buscarItensPorIdCotacao(cotacao.getId());


        
 	    Integer cont = 0;
 	    List<CotacaoEmpresaItem> itensVerifica = new ArrayList<CotacaoEmpresaItem>(); 
 	    
 	    
        while(cotacao.getItens().size() > chamada)
      	{
     	   CotacaoEmpresaItem itemASerVerificado = itens.get(cont);
     	   
     	   for(CotacaoEmpresaItem item : itens)
      		{
      		     if(itemASerVerificado.getItem().getId() == item.getItem().getId())
      		     {
      		    	 
      		    	 itensVerifica.add(item);
      		     }
      		}
      		cont++;
      		verificar(itensVerifica);
      		itensVerifica.clear();
      	}
		
	}
	
	@Transactional(readOnly = false)
	 private void verificar( List<CotacaoEmpresaItem> itensVerifica)
		{
   
			CotacaoEmpresaItem itemGanhador = itensVerifica.get(0);
			itemGanhador.setStatus(CotacaoEmpresaItemStatus.GANHOU);
		
		for(CotacaoEmpresaItem item: itensVerifica)
		{
			if(itemGanhador.equals(item) == false)
			{
			if(itemGanhador.getValorUnitario() > item.getValorUnitario())
			{
				itemGanhador.setStatus(CotacaoEmpresaItemStatus.PERDEU);
				item.setStatus(CotacaoEmpresaItemStatus.GANHOU);
				itemGanhador = item;
			}else if(itemGanhador.getValorUnitario().equals(item.getValorUnitario()))
			{
				item.setStatus(CotacaoEmpresaItemStatus.EMPATE);
				itemGanhador.setStatus(CotacaoEmpresaItemStatus.EMPATE);
			
			}
			else
			{
			item.setStatus(CotacaoEmpresaItemStatus.PERDEU);
			}
			}
		}
			chamada ++;
		
		}
}
