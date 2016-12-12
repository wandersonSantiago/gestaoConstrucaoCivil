package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresaItem;
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
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(CotacaoEmpresa cotacaoEmpresa) {
		
		for(CotacaoEmpresaItem item : cotacaoEmpresa.getItens())
		{
			item.setId(null);
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
	
    public void verificarItensGanhadores(Cotacao cotacao)
    {
    	List<CotacaoEmpresaItem> itens = cotaEmpresaItemReposi.buscarItensPorIdCotacao(cotacao.getId());
        
 	    Integer cont = 0;
       
        while(itensGanhadores.size() < cotacao.getItens().size())
      	{
     	   CotacaoEmpresaItem itemASerVerificado = itens.get(cont);
     	  
     	   List<CotacaoEmpresaItem> itensVerifica = new ArrayList<CotacaoEmpresaItem>(); 
    		
     	   
     	   for(CotacaoEmpresaItem item : itens)
      		{
      		     if(itemASerVerificado.getItem().getId() == item.getItem().getId())
      		     {
      		    	itensVerifica.add(item);
      		     }
      		}
      		cont++;
      		verificar(itensVerifica);
      	}
 	}
    private void verificar( List<CotacaoEmpresaItem> itensVerifica)
	{
		  CotacaoEmpresaItem itemGanhador = itensVerifica.get(0);
		   for(CotacaoEmpresaItem item : itensVerifica)
		   {
			   if(itemGanhador.getValorUnitario() > item.getValorUnitario())
			   {
				   itemGanhador = null;
				   itemGanhador = item;
			   }
		   }
		   itensGanhadores.add(itemGanhador);
	}
  }
