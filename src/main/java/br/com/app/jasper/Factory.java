package br.com.app.jasper;

import java.util.ArrayList;
import java.util.List;

import br.com.app.entity.almoxarifado.Cotacao;
public class Factory {
	
	@SuppressWarnings("deprecation")
	public static List<Cotacao> load(){
		
		List<Cotacao> cotacoes = new ArrayList<>();
		return cotacoes;
	}

}
