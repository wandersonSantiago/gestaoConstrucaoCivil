package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Auditoria;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoMovimentacaoEnum;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.AuditoriaService;

@RestController
@RequestMapping("/rest/almoxarifado/auditoria")
public class AuditoriaRestController {
	
	@Autowired
	private AuditoriaService auditoriaService;

	
	@GetMapping(value = "/estoque/")
	public ResponseEntity<Page<Auditoria>> entrada(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults, @RequestParam(value="tipo") TipoMovimentacaoEnum tipo) {
		Page<Auditoria> auditoria = auditoriaService.findByEmpreendimento_idAndTipoMovimentacao(tipo, new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Auditoria>>(auditoria, HttpStatus.OK);
	}
	
	@GetMapping(value="/entrada")
	public ResponseEntity<List<Auditoria>> entrada() {
		List<Auditoria> auditoria = auditoriaService.findByTipoMovimentacao(TipoMovimentacaoEnum.ENTRADA_ESTOQUE);
		return new ResponseEntity<List<Auditoria>>(auditoria, HttpStatus.OK);
	}
	
	@GetMapping(value="/saida")
	public ResponseEntity<List<Auditoria>> saida() {
		List<Auditoria> auditoria = auditoriaService.findByTipoMovimentacao(TipoMovimentacaoEnum.SAIDA_ESTOQUE);
		return new ResponseEntity<List<Auditoria>>(auditoria, HttpStatus.OK);
	}
	
	@GetMapping(value="/transferencia/entrada")
	public ResponseEntity<List<Auditoria>> transferenciaEntrada() {
		List<Auditoria> auditoria = auditoriaService.findByTipoMovimentacao(TipoMovimentacaoEnum.TRASFERENCIA_ESTOQUE_ENTRADA);
		return new ResponseEntity<List<Auditoria>>(auditoria, HttpStatus.OK);
	}
	
	@GetMapping(value="/transferencia/saida")
	public ResponseEntity<List<Auditoria>> transferenciaSaida() {
		List<Auditoria> auditoria = auditoriaService.findByTipoMovimentacao(TipoMovimentacaoEnum.TRASFERENCIA_ESTOQUE_SAIDA);
		return new ResponseEntity<List<Auditoria>>(auditoria, HttpStatus.OK);
	}
	
	@GetMapping(value="/requisicao")
	public ResponseEntity<List<Auditoria>> requisicao() {
		List<Auditoria> auditoria = auditoriaService.findByTipoMovimentacao(TipoMovimentacaoEnum.REQUISICAO);
		return new ResponseEntity<List<Auditoria>>(auditoria, HttpStatus.OK);
	}
	
	
}
