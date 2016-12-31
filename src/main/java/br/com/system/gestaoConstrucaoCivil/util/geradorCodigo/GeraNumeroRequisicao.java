package br.com.system.gestaoConstrucaoCivil.util.geradorCodigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.InformacaoRequisicaoRepository;

@Service
public class GeraNumeroRequisicao extends GeraCodigo{

	@Autowired
	private InformacaoRequisicaoRepository requisicaoRepository;
	private Integer numeroRequisicao;
	public GeraNumeroRequisicao() {
		super(100000,999999);
	
	}
    public Integer gerarNumeroRequisicao()
    {
    	for(Integer tentativa = 0 ; tentativa <= 99999; tentativa++)
    	{
    		numeroRequisicao  =   gerarNumero();
    		/*if(requisicaoRepository.existeNnumeroRequisicao(numeroRequisicao) == false ){
    		   
    			  return numeroRequisicao;
    		}*/
    	}
    	
    	return numeroRequisicao;
    }
}
