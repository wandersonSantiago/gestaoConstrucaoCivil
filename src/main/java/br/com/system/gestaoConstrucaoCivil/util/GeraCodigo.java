package br.com.system.gestaoConstrucaoCivil.util;

import java.util.Random;


public abstract class GeraCodigo {

	
	private Integer gerar(Integer minimo ,Integer maximo)
	{
		Random random = new Random();
		Integer codigo  = random.nextInt((maximo - minimo) + 1) + minimo;
		return codigo;
	}
	public Integer gerarCodigoProduto()
	{
		return gerar(100000,999999);
	}
	public Integer gerarNumeroTransferencia()
	{
		return gerar(100000,999999);
	}
	public Integer gerarNumeroRequisicao()
	{
		return gerar(1000,9999);
	}
	public String gerarCodigoBarra(Integer codigo)
	{
		return "0000000".concat(codigo.toString());
	}
	
}
