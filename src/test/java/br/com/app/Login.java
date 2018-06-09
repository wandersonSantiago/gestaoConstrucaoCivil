package br.com.app;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class Login extends AbstractMvcTest {

	 
	
	@Before
	public void setUp() throws Exception {
	 
		
	}
	
	@Test
	public void loginOK() throws IOException, Exception {
		login().andExpect(authenticated());
	}
	
	@Test
	public void loginInvalid() throws IOException, Exception{
	
		login("root","321").andExpect(unauthenticated());
		
	}
	
	
}
