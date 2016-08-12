package br.com.system.gestaoConstrucaoCivil.criarObjecto;

import java.util.Date;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;

public class CriaEmpreendimento {

	private Empreendimento empreendimento;
	public CriaEmpreendimento()
	{
		empreendimento = new Empreendimento();
		empreendimento.setDescricao("Construcao de Casa");
		empreendimento.setAtivo(true);
		//empreendimento.setCrea("45456456456");
		empreendimento.setDataAbertura(new Date());
		empreendimento.setDatafechamento(null);
		empreendimento.setEnderecoEmpreendimento(new CriaEndereco().getEndereco());
        empreendimento.setTipoEmpreendimento(TipoEmpreendimentoEnum.CONDOMINIO_DE_EDIFICIO_RESIDENCIAL);
        empreendimento.setValoresGastos(0.0);
        empreendimento.setValorMaximoGastar(200000000.0);
        empreendimento.setPorcentagem(0.0);
       
	}
	public Empreendimento getEmpreendimento()
	{
		return empreendimento;
	}
}
