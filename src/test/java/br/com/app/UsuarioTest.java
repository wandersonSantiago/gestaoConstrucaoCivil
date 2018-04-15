package br.com.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class UsuarioTest extends AbstractMvcTest {

	String user = "{'login':'root','senha':'121232133'}";
	
	/*@Test
	public void loginNok() throws Exception {
		loginTeste().andExpect(status().isUnauthorized());
	}*/
	
	@Test
	public void save() {
		
	}
	@Test
	public void loginOk() throws Exception  {
		
		login().andExpect(status().isOk());
	}
	
}
