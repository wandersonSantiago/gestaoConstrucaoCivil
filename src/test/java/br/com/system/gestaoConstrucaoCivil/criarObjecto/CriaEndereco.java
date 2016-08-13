package br.com.system.gestaoConstrucaoCivil.criarObjecto;



import br.com.system.gestaoConstrucaoCivil.entity.Endereco;

public class CriaEndereco {


    private	Endereco endereco ;
	
	public CriaEndereco()
	{
		endereco = new Endereco();
		endereco.setBairro("Jardim Vila Santana");
		endereco.setLogradouro("Rua fradique coutinho");
		endereco.setNumero(97);
		endereco.setLocalidade("Valinhos");
		endereco.setCep("13270021");
		endereco.setUf("SP");
		endereco.setComplemento("Fundo");
	}
   public Endereco getEndereco()
   {
	   return endereco;
   }
}
