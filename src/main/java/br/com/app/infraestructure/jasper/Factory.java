package br.com.app.infraestructure.jasper;

import java.util.ArrayList;
import java.util.List;

import br.com.app.financeiro.domain.model.Cotacao;
public class Factory {
	
	public static List<Cotacao> load(){
		
		List<Cotacao> cotacoes = new ArrayList<>();
		return cotacoes;
	}

}
