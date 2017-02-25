package br.com.system.gestaoConstrucaoCivil.service;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.system.gestaoConstrucaoCivil.entity.Permissao;
import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoModulo;

@Component
public class CriaPermissaoes {

	private List<Permissao> permissoes;
	
	@EventListener(ContextRefreshedEvent.class)
	void contextRefreshedEvent() {
	       
	  lerJsonPermissoes();
	//	converteObjetoJson();
    }
	
	private void lerJsonPermissoes()
	{
		
        ObjectMapper mapper = new ObjectMapper();
		
		try{
			
			permissoes = mapper.readValue(new File("./src/config/permissoes.json"),mapper.getTypeFactory().constructCollectionType(List.class,Permissao.class));
		
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	private void converteObjetoJson()
	{
		
		List<Permissao> obj = new ArrayList<Permissao>();
		
		
		List<PerfilUsuarioEnum> l = Arrays.asList(PerfilUsuarioEnum.values());
		
		
		
		l.forEach(p->{
			
			Permissao permissao = new Permissao();
			permissao.setDescricao(p.getDescricao());
			permissao.setRol(p.toString());
			permissao.setTipoModulo(TipoModulo.MODULO_CHAMADO);
			obj.add(permissao);
		});
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			mapper.writeValue(new File("./src/config/permissoes.json"),obj);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("OK");
	}
}
