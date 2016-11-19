package br.com.system.gestaoConstrucaoCivil.util;

import java.util.Random;


public class GeraCodigo {

	
	private  Integer maximo = 0;
	private  Integer minimo = 0;
	
	public GeraCodigo (Integer minimo,Integer maximo){
		
		this.minimo = minimo;
		this.maximo = maximo;
	}
	
	private Integer gerar()
	{
		Random random = new Random();
		Integer codigo  = random.nextInt((maximo - minimo) + 1) + minimo;
		return codigo;
	}
	
	public Integer gerarNumeroTransferencia()
	{
		return gerar();
	}
	public Integer gerarNumeroRequisicao()
	{
		return gerar();
	}
	public String gerarCodigoBarra(Integer codigo)
	{
		return "0000000".concat(codigo.toString());
	}
	
}
