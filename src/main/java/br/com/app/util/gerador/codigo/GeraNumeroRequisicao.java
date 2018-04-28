package br.com.app.util.gerador.codigo;

import org.springframework.stereotype.Service;

@Service
public class GeraNumeroRequisicao extends GeraCodigo{

	public GeraNumeroRequisicao() {
		super(100000,999999);
	
	}
    public Integer gerarNumeroRequisicao()
    {
    	Integer numeroRequisicao = 0;
    	for(Integer tentativa = 0 ; tentativa <= 99999; tentativa++)
    	{
    		numeroRequisicao  =   gerarNumero();
    		
    	}
    	
    	return numeroRequisicao;
    }
}
