package br.com.system.gestaoConstrucaoCivil.pojo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.EstoqueEmpreendimentoException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(EstoqueEmpreendimentoException.class)
	@RequestMapping(value = "/rest/produtoEstoque/alteraProduto", method = RequestMethod.PUT)
	public ResponseEntity handleException(EstoqueEmpreendimentoException e) {
       System.out.println("OIOIOIOIOI");
       return new ResponseEntity("OI TUDO BEM", HttpStatus.CREATED);
    }  
}
