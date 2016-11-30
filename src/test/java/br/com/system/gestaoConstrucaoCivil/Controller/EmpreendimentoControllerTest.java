package br.com.system.gestaoConstrucaoCivil.Controller;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.GestaoConstrucaoCivilApplication;
import br.com.system.gestaoConstrucaoCivil.criarObjecto.CriaEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;
import br.com.system.gestaoConstrucaoCivil.web.controller.EmpreendimentoRestController;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GestaoConstrucaoCivilApplication.class)
@WebIntegrationTest
public class EmpreendimentoControllerTest  extends TestCase{

	@Autowired
	private EmpreendimentoRestController empreendimentoRestController;
	private UriComponentsBuilder ucBuilder;
	
	public EmpreendimentoControllerTest()
	{
		ucBuilder = UriComponentsBuilder.newInstance();
	    ucBuilder.host("localhost");
		ucBuilder.port(8080);
		ucBuilder.scheme("http");
	}
	@Test
	public void cadastrarEmpreendimento(){
			
		ResponseEntity responseEntity =  empreendimentoRestController.salvar(new CriaEmpreendimento().getEmpreendimento(), ucBuilder);
		assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
	}
	//@Test
	public void buscarTiposEmpreendimento()
	{
		 ResponseEntity<Iterable<TipoEmpreendimentoEnum>> responseEntity = empreendimentoRestController.tiposEmpreendimentos();       	
		 
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 
		 Iterator iterator = responseEntity.getBody().iterator();
         
		 while(iterator.hasNext())
		 {
			 TipoEmpreendimentoEnum tipo = (TipoEmpreendimentoEnum) iterator.next();
			 System.out.println("Tipo:" + tipo.getDescricao());
		 }
		 
	}
	//@Test
	public void buscarEmpreendimentoPorId()
	{
		ResponseEntity responseEntity = empreendimentoRestController.buscarPorId(348L);
	     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   /*  Empreendimento empreendimento = (Empreendimento) responseEntity.getBody();
	     assertEquals("Alteração na descrição", empreendimento.getDescricao()); */
	}
	

	public void alterarEmpreendimento()
	{
		ResponseEntity responseEntity = empreendimentoRestController.buscarPorId(348L);
		Empreendimento empreendimento = (Empreendimento) responseEntity.getBody();
		empreendimento.setDescricao("Alteração na descrição2");
		
		responseEntity = null;
	    responseEntity	=  empreendimentoRestController.alterar(empreendimento, ucBuilder);
		
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
}
