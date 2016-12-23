package br.com.system.gestaoConstrucaoCivil.pojo;

import java.util.ArrayList;
import java.util.List;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Item;


public class CriaListaEntradaOuBaixa<E> {

	
	public List<EntradaOuBaixa> criarListaDeEntradaOuBaixa(List<E> objetos,Empreendimento empreendimento)
	{
	   List<Item> itens = (List<Item>) objetos;	
	   List<EntradaOuBaixa> entradasOuBaixas = new ArrayList<EntradaOuBaixa>();
	   
	   itens.forEach(item -> {
		   
		   EntradaOuBaixa entradaOuBaixa = new  EntradaOuBaixa(item.getProduto(),item.getQuantidade(),empreendimento);
		   entradasOuBaixas.add(entradaOuBaixa);
	   });
	   return entradasOuBaixas;
	}
	
}
