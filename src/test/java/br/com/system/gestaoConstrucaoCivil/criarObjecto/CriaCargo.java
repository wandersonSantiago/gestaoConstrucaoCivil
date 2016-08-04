package br.com.system.gestaoConstrucaoCivil.criarObjecto;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;

public class CriaCargo {

	private Cargo cargo;
	
	public CriaCargo()
	{
		cargo = new Cargo();
		cargo.setDescricao("Analista");
	}
	public Cargo getCargo()
	{
		return cargo;
	}
}
