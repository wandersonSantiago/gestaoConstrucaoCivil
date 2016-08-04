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
import br.com.system.gestaoConstrucaoCivil.criarObjecto.CriaEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.criarObjecto.CriaFuncionario;
import br.com.system.gestaoConstrucaoCivil.web.controller.FuncionarioRestController;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GestaoConstrucaoCivilApplication.class)
@WebIntegrationTest
public class FuncionarioControllerTest extends TestCase {
	@Autowired
	private FuncionarioRestController  funcionarioController;
	private UriComponentsBuilder ucBuilder;

	public FuncionarioControllerTest() {
		ucBuilder = UriComponentsBuilder.newInstance();
		ucBuilder.host("localhost");
		ucBuilder.port(8080);
		ucBuilder.scheme("http");

	}

	@Test
	public void cadastrarEmpreendimento() {

		ResponseEntity responseEntity =  funcionarioController.salvar(new CriaFuncionario().getFuncionario(), ucBuilder);
		assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
	}
}
