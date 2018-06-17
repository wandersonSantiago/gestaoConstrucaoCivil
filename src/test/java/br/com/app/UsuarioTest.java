package br.com.app;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.hibernate.mapping.Collection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.app.entity.Usuario;
import br.com.app.generate.GenerateUsuario;

public class UsuarioTest extends AbstractMvcTest {

	private final String uriSave = "/rest/usuario";
	private final String uriList = "/rest/usuario";
	private final String uriEdit = "/rest/usuario";
	private final String uriFindById = "/rest/usuario";
	
	
	@Before
	public void setUp() throws Exception {
		
		
	}
	 
	@Test
	public void saveNoLogin() throws Exception {

		Usuario usuario = new GenerateUsuario().gerar();
		
		mockMvc.perform(post(uriSave).content(Util.getInstance().objectForJson(usuario)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isForbidden());
	 
	}
	@Test
	public void save() throws Exception {

		Usuario usuario = new GenerateUsuario().gerar();
		
		mockMvc.perform(post(uriSave).with(csrf()).content(Util.getInstance().objectForJson(usuario)).with(httpBasic("root","123")).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	 
	}
	
	@Test
	public void edit() throws Exception {

		 
	}

	@Test
	public void list() throws Exception {

	 
				 
	}

	@Test
	public void findById() throws Exception {

		

	}
	
	 
}
