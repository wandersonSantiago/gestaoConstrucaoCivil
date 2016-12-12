package br.com.system.gestaoConstrucaoCivil.util.geradorCodigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.InformacaoRequisicaoRepository;

@Component
public class GeraNumeroRequisicao extends GeraCodigo{

	@Autowired
	private InformacaoRequisicaoRepository requisicaoRepository;
	private Integer numeroRequisicao;
	public GeraNumeroRequisicao() {
		super(100000,999999);
	
	}
    public Integer gerar()
    {
    	for(Integer tentativa = 0 ; tentativa <= 99999; tentativa++)
    	{
    		numeroRequisicao  =   gerar();
    		/*if(requisicaoRepository.existeNnumeroRequisicao(numeroRequisicao) == false ){
    		   
    			  return numeroRequisicao;
    		}*/
    	}
    	return 1;
    }
}
