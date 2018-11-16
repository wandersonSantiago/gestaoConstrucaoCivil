/*package br.com.app.generate;

import java.util.Date;
import java.util.Random;

import br.com.app.entity.Empreendimento;
import br.com.app.enuns.StatusEmpreendimento;

public class GenerateEmpreendimento {

	public Empreendimento gerar() {
		
		Empreendimento emp = new Empreendimento();
		emp.setEndereco(new GenerateEndereco().gerar());
	    emp.setDescricao("Descricao Empreendimento " + new Random().nextInt(10000));
		emp.setDataAbertura(new Date());
		emp.setPorcentagem(0.0);
		emp.setStatus(StatusEmpreendimento.EM_ANDAMENTO);
		emp.setTelefone("39874456");
		emp.setValoresGastos(1000.01);
		emp.setValorMaximoGastar(10000000.0);
		return emp;
	}
}
*/