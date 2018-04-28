package br.com.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class AbstractMvcTest {

	protected MockMvc mockMvc;
	private static Set<Class> inited = new HashSet<>();
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Before
	public void init() throws Exception {
		if (!inited.contains(getClass())) {
			inited.add(getClass());
		}
	}

	protected String json(Object o) throws IOException {
		return mapper.writeValueAsString(o);
	}

	protected <T> T json(String jsonStringResponse, Class<T> classe)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStringResponse, classe);
	}

	protected ResultActions login(String username, String password) throws IOException, Exception {
		return mockMvc.perform(post("#/rest/usuario/usuarios").content(json("{'login':'root','senha':'123'}"))
				.contentType(MediaType.APPLICATION_JSON));

	}

	protected ResultActions login() throws IOException, Exception {
		return mockMvc.perform(get("/rest/usuario/usuario").content(json("{'login':'root','senha':'121232133'}"))
				.contentType(MediaType.APPLICATION_JSON));

	}

	protected String extractToken(MvcResult result) throws UnsupportedEncodingException {
		return result.getResponse().getHeader("Authorization");
	}

}
