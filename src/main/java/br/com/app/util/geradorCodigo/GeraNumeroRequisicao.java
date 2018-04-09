package br.com.app.util.geradorCodigo;

import org.springframework.stereotype.Service;

@Service
public class GeraNumeroRequisicao extends GeraCodigo{

	/*@Autowired
	private InformacaoRequisicaoRepository requisicaoRepository;*/
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
