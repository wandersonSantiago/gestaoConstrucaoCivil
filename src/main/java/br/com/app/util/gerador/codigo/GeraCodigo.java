package br.com.app.util.gerador.codigo;

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
		return random.nextInt((maximo - minimo) + 1) + minimo;
		 
	}
}
