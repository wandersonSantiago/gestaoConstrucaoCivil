package br.com.system.gestaoConstrucaoCivil.Controller;

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
import br.com.system.gestaoConstrucaoCivil.criarObjecto.CriaEmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.entity.DadoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.entity.Endereco;
import br.com.system.gestaoConstrucaoCivil.service.EmpresaContratanteService;
import br.com.system.gestaoConstrucaoCivil.web.controller.EmpresaContratanteRestController;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GestaoConstrucaoCivilApplication.class)
@WebIntegrationTest
public class EmpresaContratanteControllerTest extends TestCase {

	@Autowired
	private EmpresaContratanteRestController empresaContratanteRestController;
	private UriComponentsBuilder ucBuilder;
	
	 public EmpresaContratanteControllerTest() {
	
		    ucBuilder = UriComponentsBuilder.newInstance();
		    ucBuilder.host("localhost");
			ucBuilder.port(8080);
			ucBuilder.scheme("http");
	}
	

	//@Test
	public void cadastrarEmpresaContratante(){
		
		
		EmpresaContratante empresaContratante = new CriaEmpresaContratante().getEmpresaContratante();
		 ResponseEntity responseEntity  = empresaContratanteRestController.salvarEmpresaContratante(empresaContratante, ucBuilder);
		 assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
	
	}
	//@Test
	public void alterarEmpresaConrtatante()
	{
		 ResponseEntity responseEntity  = empresaContratanteRestController.buscarEmpresaPorId(352L);
		 EmpresaContratante empresaContratante = (EmpresaContratante) responseEntity.getBody();
		 empresaContratante.getDadoEmpresa().setNomeFantasia("Construcão do Du");
		 empresaContratante.getDadoEmpresa().getEndereco().setBairro("Jr do Santo");
		 
		 responseEntity = null;
		 responseEntity  = empresaContratanteRestController.alterarEmpresaContratante(empresaContratante, ucBuilder);
	     assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());		
	}
	//@Test
	public void buscarTodasEmpresaContratante()
	{
	   ResponseEntity<Iterable<EmpresaContratante>> responseEntity = empresaContratanteRestController.buscarEmpresaContratante();          	
	   
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());	
	}
	//@Test
	public void buscarEmpresaContratantePorId()
	{
      ResponseEntity responseEntity = empresaContratanteRestController.buscarEmpresaPorId(1000L);
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}