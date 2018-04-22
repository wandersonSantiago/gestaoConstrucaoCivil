package br.com.app;

import java.io.IOException;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.DadoEmpresa;
import br.com.app.entity.EmpresaContratante;
import br.com.app.entity.Endereco;
import br.com.app.enuns.UfEnum;

public class EmpresaContratanteTest extends AbstractMvcTest{

	@Test
	@Transactional
	public void save() throws Exception {
		
		build();
 	
	}
	
	private String build() throws IOException {
		
		EmpresaContratante e = new EmpresaContratante();
		DadoEmpresa d = new DadoEmpresa();
		d.setCnpj("28.741.362/0001-52");
		d.setInscricaoEstadual("626.077.274.008");
		d.setEmail("root@suporte.com.br");
		d.setNomeFantasia("Empresa de Teste");
		d.setRazaoSocial("Empresa de Teste LTDA");
		d.setUfIe(UfEnum.SP);
		d.setTelefone("3924-8079");
		Endereco endereco = new Endereco();
		endereco.setBairro("Vila Nicácio");
		endereco.setCep("14405-118");
		endereco.setNumero(272);
		endereco.setLogradouro("Rua Torquato Caleiro");
		endereco.setUf("SP");
		d.setEndereco(endereco);
	    return json(e);
	
	}
}
