/*package br.com.app;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.app.entity.Cargo;
import br.com.app.generate.GenerateCharge;

 
public class CargoTest extends AbstractMvcTest {

	private final String uriSave = "/rest/recursosHumanos/cargo/salva";
	private final String uriList = "/rest/recursosHumanos/cargo/lista";
	private final String uriEdit = "/rest/recursosHumanos/cargo/altera";
	private final String uriFindById = "/rest/recursosHumanos/cargo/buscaPorId/{id}";
	
	
	@Before
	public void set() throws Exception {
		 
		save();
	}
	 
	@Test
	public void save() throws Exception {

		
		Cargo cargo = new GenerateCharge().gerar();

		mockMvc.perform(post(uriSave).with(csrf()).content(Util.getInstance().objectForJson(cargo)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	public void edit() throws Exception {

		String cargosJson = mockMvc.perform(get(uriList))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(IsNull.notNullValue()))
				.andReturn().getResponse()
				.getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		List<Cargo> cargos = mapper.readValue(cargosJson, new TypeReference<List<Cargo>>() {
		});

		Cargo cargoEdited = cargos.get(0);
	
		
		cargoEdited.setDescricao("Cargo Editado");

		mockMvc.perform(put(uriEdit).with(csrf()).content(Util.getInstance().objectForJson(cargoEdited)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mockMvc.perform(get(uriFindById, cargoEdited.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.id").value(cargoEdited.getId().intValue()))
				.andExpect(jsonPath("$.descricao").value(cargoEdited.getDescricao()));
	}

	@Test
	public void list() throws Exception {

		mockMvc.perform(get(uriList)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").value(IsNull.notNullValue()));
				 
	}

	@Test
	public void findById() throws Exception {

		String cargosJson = mockMvc.perform(get(uriList)).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		List<Cargo> cargos = mapper.readValue(cargosJson, new TypeReference<List<Cargo>>() {
		});
		Cargo cargo = cargos.get(0);

		mockMvc.perform(get(uriFindById, cargo.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.id").value(cargo.getId().intValue()))
				.andExpect(jsonPath("$.descricao").value(cargo.getDescricao()));

	}
	

}
*/