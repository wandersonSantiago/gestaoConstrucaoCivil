package br.com.app;
 

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

import br.com.app.entity.almoxarifado.TipoProduto;
import br.com.app.generate.GenerateTipoProduto;
 
public class TipoProdutoTest  extends AbstractMvcTest {

	private final String uriSave = "/rest/almoxarifado/produto/tipo/salva";
	private final String uriList = "/rest/almoxarifado/produto/tipo/lista";
	private final String uriEdit = "/rest/almoxarifado/produto/tipo/altera";
	private final String uriFindById = "/rest/almoxarifado/produto/tipo/buscaPorId/{id}";

	@Before
	public void set() throws Exception {
		 
		save();
	}
	
	@Test
	public void save() throws Exception {

		TipoProduto tipoProduto = new GenerateTipoProduto().gerar();

		mockMvc.perform(post(uriSave).with(csrf()).content(Util.getInstance().objectForJson(tipoProduto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		 
	}

	@Test
	public void edit() throws Exception {

		String tipoProdutoJson = mockMvc.perform(get(uriList))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(IsNull.notNullValue()))
				.andReturn().getResponse()
				.getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		List<TipoProduto> tipoProdutos = mapper.readValue(tipoProdutoJson, new TypeReference<List<TipoProduto>>() {
		});

		TipoProduto tipoProdutoEdited = tipoProdutos.get(0);
	
		
		tipoProdutoEdited.setDescricao("Tipo Produto Editado");

		mockMvc.perform(put(uriEdit).with(csrf()).content(Util.getInstance().objectForJson(tipoProdutoEdited)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mockMvc.perform(get(uriFindById, tipoProdutoEdited.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.id").value(tipoProdutoEdited.getId().intValue()))
				.andExpect(jsonPath("$.ativo").value(tipoProdutoEdited.isAtivo()))
				.andExpect(jsonPath("$.descricao").value(tipoProdutoEdited.getDescricao()));
		
	}

	@Test
	public void list() throws Exception {

		mockMvc.perform(get(uriList)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").value(IsNull.notNullValue()));

	}

	@Test
	public void findById() throws Exception {

		String tipoProdutoJson = mockMvc.perform(get(uriList).with(csrf())).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		List<TipoProduto> tipoProdutos = mapper.readValue(tipoProdutoJson, new TypeReference<List<TipoProduto>>() {
		});
		TipoProduto tipoProduto = tipoProdutos.get(0);

		mockMvc.perform(get(uriFindById, tipoProduto.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.id").value(tipoProduto.getId().intValue()))
				.andExpect(jsonPath("$.ativo").value(tipoProduto.isAtivo()))
				.andExpect(jsonPath("$.descricao").value(tipoProduto.getDescricao()));
		
	}
	
	 
}
