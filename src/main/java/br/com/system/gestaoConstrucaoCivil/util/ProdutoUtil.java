package br.com.system.gestaoConstrucaoCivil.util;

import java.util.Random;

public class ProdutoUtil {
	
	private final Integer maximo = 999999;
	private final Integer minimo = 100000;
	
	public Integer gerarCodigo()
	{
		
		Random random = new Random();
			
		Integer codigo  = random.nextInt((maximo - minimo) + 1) + minimo;
		return codigo;
	}
	
	public String gerarCodigoBarra(Integer codigo)
	{
		return "0000000".concat(codigo.toString());
	}
}
