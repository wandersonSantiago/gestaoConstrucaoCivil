package br.com.system.gestaoConstrucaoCivil.criarObjecto;

import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;

public class CriaEmpresaContratante {
	
	
	private EmpresaContratante empresaContratante;	
	private CriaDadoEmpresa dadoEmpresa;
	
	public CriaEmpresaContratante()
	{
		empresaContratante = new EmpresaContratante();
		dadoEmpresa = new CriaDadoEmpresa();
		empresaContratante.setDadoEmpresa(dadoEmpresa.getDadoEmpresa());
	}
	public EmpresaContratante getEmpresaContratante()
	{
		return empresaContratante;
	}
}
