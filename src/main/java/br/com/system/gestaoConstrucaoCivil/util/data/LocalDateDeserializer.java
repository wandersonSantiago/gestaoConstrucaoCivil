package br.com.system.gestaoConstrucaoCivil.util.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate>{

	 protected LocalDateDeserializer() {
	        super(LocalDate.class);
	    }
     @Override
	 public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
	            throws IOException, JsonProcessingException {
	      
    	String dataFront = jp.readValueAs(String.class).toString();
    	
        if(dataFront.contains("T"))
        {
    	Integer posicao = dataFront.indexOf("T"); 
    	
        String dataFormat = dataFront.substring(0, posicao); 
        return LocalDate.parse(dataFormat);
        }
        
        return LocalDate.parse(jp.readValueAs(String.class));
        
        
	    }

	
}
