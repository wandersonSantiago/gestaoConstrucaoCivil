package br.com.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import br.com.app.entity.Usuario;

public class UsuarioTest extends AbstractMvcTest {

	@Test
	public void save() {

		Usuario user = new Usuario();
		user.setAtivo(true);
		user.setEmail("josesilva@suport.com.br");
		user.setNome("Jose Silva");
		user.setSenha("78654");
		user.setLogin("josesilva");

	}

	@Test
	public void loginOk() throws Exception {

		login().andExpect(status().isOk());
	}

}
