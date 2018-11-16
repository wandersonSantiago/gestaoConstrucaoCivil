/*package br.com.app;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;

import org.junit.Test;
import org.springframework.http.MediaType;

public class Login extends AbstractMvcTest {

	 
	@Test
	public void loginOK() throws IOException, Exception {
		mockMvc.perform(get("/rest/usuario/usuario").with(httpBasic("root", "123"))
				.contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void loginInvalid() throws IOException, Exception{
		
		mockMvc.perform(get("/rest/usuario/usuario").with(httpBasic("root","123"))
				.contentType(MediaType.APPLICATION_JSON));
		
	}
	
	
}
*/