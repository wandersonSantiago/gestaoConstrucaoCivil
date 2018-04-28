package br.com.app.util.gerador.codigo;

import org.springframework.stereotype.Service;

@Service
public class GereCodigoPacoteServico extends GeraCodigo {

 
	public GereCodigoPacoteServico() {
		
		super(10000,99999);
		
	}
	public Integer gerarNumeroPacote()
	{
		return gerarNumero();
		
	}
}
