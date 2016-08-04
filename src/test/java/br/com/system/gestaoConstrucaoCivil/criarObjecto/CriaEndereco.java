package br.com.system.gestaoConstrucaoCivil.criarObjecto;



import br.com.system.gestaoConstrucaoCivil.entity.Endereco;

public class CriaEndereco {


    private	Endereco endereco ;
	
	public CriaEndereco()
	{
		endereco = new Endereco();
		endereco.setBairro("Jardim Vila Santana");
		endereco.setRua("Rua fradique coutinho");
		endereco.setNumero(97);
		endereco.setCidade("Valinhos");
		endereco.setCep("13270021");
		endereco.setEstado("SP");
		endereco.setCidade("Valinhos");
		endereco.setComplemento("Fundo");
	}
   public Endereco getEndereco()
   {
	   return endereco;
   }
}
