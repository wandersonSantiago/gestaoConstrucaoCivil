package br.com.system.gestaoConstrucaoCivil.util.geradorCodigo;

import java.util.Random;


public abstract class GeraCodigo {

	private  Integer maximo = 0;
	private  Integer minimo = 0;
	
	public GeraCodigo(Integer minimo,Integer maximo)
	{
		this.maximo = maximo;
		this.minimo = minimo;
	}
	protected Integer gerarNumero()
	{
		Random random = new Random();
		Integer codigo  = random.nextInt((maximo - minimo) + 1) + minimo;
		return codigo;
	}
}
