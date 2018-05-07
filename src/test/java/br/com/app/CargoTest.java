package br.com.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Cargo;

public class CargoTest extends AbstractMvcTest {

	@Test
	@Transactional
	public void save() throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rest/recursosHumanos/cargo/salva")
				.contentType(MediaType.APPLICATION_JSON).content(buildCargo());
		mockMvc.perform(builder).andExpect(status().isCreated());
	}
	
	
	@Test
	public void list() throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/rest/recursosHumanos/cargo/lista")
				.contentType(MediaType.APPLICATION_JSON).content(buildCargo());
		 mockMvc.perform(builder).andExpect(status().isOk());
		 /*.andExpect(jsonPath("[0].descricao").value("Cargo Teste"));*/
	
		 
	}
 
	private String buildCargo() throws IOException {

		Cargo cargo = new Cargo();
		cargo.setDescricao("Cargo Teste");
		return json(cargo);

	}
}
