package br.com.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.app.entity.Empreendimento;
import br.com.app.generate.GenerateEmpreendimento;

public class EmpreendimentoTest extends AbstractMvcTest {

	private final String uriSave = "/rest/empreendimento/";
	private final String uriList = "/rest/empreendimento/";
	private final String uriEdit = "/rest/empreendimento/altera";
	private final String uriFindById = "/rest/empreendimento/{id}";
	private final String uriFindByDescricao = "/rest/empreendimento/";
	private final String uriListStatus = "/rest/empreendimento/status";
	
	@Before
	public void setUp() throws Exception {
		save();

	}

	@Test
	public void save() throws Exception {

		 
	/*	Empreendimento empreendimento = new GenerateEmpreendimento().gerar();

		mockMvc.perform(post(uriSave).content(objectForJson(empreendimento)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());*/
		
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
