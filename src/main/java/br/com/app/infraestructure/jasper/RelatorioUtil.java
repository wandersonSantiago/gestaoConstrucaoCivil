package br.com.app.infraestructure.jasper;

import java.net.URL;
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

	public String caminhoArquivoCotacaoVencedoresItens() {
		URL url = getClass().getResource("/relatorio/compras/item.cotacao.vencedores.jasper");
		return url.toString();
	}
	
	public String caminhoArquivoCotacaoEmpresa() {
		return "/relatorio/compras/cotacao.empresa.jrxml";
	}
	
	public String caminhoArquivoCotacaoEmpresaItens() {
		URL url = getClass().getResource("/relatorio/compras/item.cotacao.empresa.jasper");
		return url.toString();
	}
	
	public String caminhoArquivoLancamentos() {
		return "/relatorio/financeiro/lancamentos.jrxml";
	}
	
	public String caminhoArquivoLancamentosItens() {
		URL url = getClass().getResource("/relatorio/financeiro/item.lancamentos.jasper");
		return url.toString();
	}
	
	public  HashMap<String, Object> caminhoMapaDeLogos(HashMap<String, Object> hashMap) {
		
		return hashMap;
	}

}
