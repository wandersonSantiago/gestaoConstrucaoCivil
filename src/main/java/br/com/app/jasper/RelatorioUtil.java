package br.com.app.jasper;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class RelatorioUtil {
	
	
	public  String caminhoArquivoCotacao() {
		return "/relatorio/compras/cotacao.jrxml";
	}
	
	public  String caminhoArquivoCotacaoVencedores() {
		return "/relatorio/compras/cotacao.vencedores.jrxml";
	}

	public String caminhoArquivoCotacaoEmpresa() {
		return "/relatorio/compras/cotacao.empresa.jrxml";
	}
	
	public  HashMap<String, Object> caminhoMapaDeLogos() {
		HashMap<String, Object> hashMap = new HashMap<>();
		return hashMap;
	}

	

}
