package br.com.app;

import org.junit.Test;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Endereco;

public class EmpreendimentoTest extends AbstractMvcTest {

	@Test
	public void save() {
		
		 Empreendimento emp = new Empreendimento();
		 Endereco endereco = new Endereco();
		 endereco.setBairro("Jardim Chapadão");
		 endereco.setCep("13070-060");
		 endereco.setUf("SP");
		 endereco.setLogradouro("Rua Presidente João Goulart");
		 endereco.setNumero(78);
		 
		 emp.setEndereco(endereco);
		 emp.setDescricao("Matriz");
		 
		
	}
}
