package br.com.system.gestaoConstrucaoCivil.criarObjecto;


import br.com.system.gestaoConstrucaoCivil.entity.DadoEmpresa;

public class CriaDadoEmpresa {

	private DadoEmpresa dadoEmpresa ;
	
	private CriaEndereco endereco;
	public CriaDadoEmpresa()
	{
		dadoEmpresa = new DadoEmpresa();
		endereco = new CriaEndereco();
        dadoEmpresa.setEndereco(endereco.getEndereco());
		dadoEmpresa.setCnpj("84.384.722/0001-87");
		dadoEmpresa.setInscricaoEstadual("895.773.612.596");
		dadoEmpresa.setNomeFantasia("Empreiteira Silva");
		dadoEmpresa.setRazaoSocial("Empreiteira Silva LTDA");
		dadoEmpresa.setTelefone("1938711154");
		dadoEmpresa.setEmail("mariajose@terra.com.br");
	}
	public DadoEmpresa getDadoEmpresa()
	{
		return dadoEmpresa;
	}
}
