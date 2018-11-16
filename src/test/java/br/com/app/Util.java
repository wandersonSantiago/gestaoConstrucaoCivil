/*package br.com.app;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.app.pojo.SessionUsuario;

public class Util {

	private static Util instance;
	private static ObjectMapper mapper;
	private Util() {
	 
	}
	public static Util getInstance() {
		if (instance == null) {
			instance = new Util();
			mapper = new ObjectMapper(); 
		}
		return instance;
	}
	
	public String objectForJson(Object o) throws IOException {
		return mapper.writeValueAsString(o);
	}

	public <T> T json(String jsonStringResponse, Class<T> classe)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStringResponse, classe);
	}
}
*/