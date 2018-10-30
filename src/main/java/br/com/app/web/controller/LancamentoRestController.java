package br.com.app.web.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.app.dto.LancamentoDTO;
import br.com.app.dto.SaldoLancamentoDTO;
import br.com.app.entity.Lancamento;
import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.enuns.CategoriaEnum;
import br.com.app.enuns.StatusLancamento;
import br.com.app.enuns.TipoLancamentoEnum;
import br.com.app.exceptions.NotFoundException;
import br.com.app.jasper.JasperReportsService;
import br.com.app.jasper.RelatorioUtil;
import br.com.app.repository.filter.CotacaoFilter;
import br.com.app.repository.filter.LancamentoFilter;
import br.com.app.service.LancamentoService;

@RestController
@RequestMapping("/rest/lancamentos")
public class LancamentoRestController {
	
	@Autowired
	private LancamentoService lancamentoService;
	@Autowired
	private JasperReportsService jasperReportsService;
	@Autowired
	private RelatorioUtil relatorioUtil;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Lancamento obj){
		obj = lancamentoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Lancamento obj, @PathVariable Long id){
		lancamentoService.update(obj, id);		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		lancamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(lancamentoService.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<LancamentoDTO>> findAll() {
		List<Lancamento> list = lancamentoService.findAll();
		List<LancamentoDTO> listDTO = list.stream().map(obj -> new LancamentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping("/filters")
	public ResponseEntity<Page<LancamentoDTO>> findAllPage(	@RequestBody LancamentoFilter filter) {		
		Page<Lancamento> list = null;
		
		if(filter.getAdicional() != null) {
			list = lancamentoService.filterAndType(filter.getAdicional(), filter.getPage().getPageRequest());
		}
		else {
			list = lancamentoService.filters(filter);
		}
		Page<LancamentoDTO> listDTO = list.map(obj -> new LancamentoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping(value = "/imprimir")
	@ResponseBody
	public byte[] filtroPdf(@RequestBody LancamentoFilter filters, HttpServletResponse response) {
		
		response.setHeader("Content-Disposition", "inline; filename=file.pdf");
	    response.setContentType("application/pdf");
	    
	    Page<Lancamento>  list = lancamentoService.filters(filters);
	    List<LancamentoDTO> listDTO = list.stream().map(obj -> new LancamentoDTO(obj)).collect(Collectors.toList());
	try {	
		return jasperReportsService.generateReport(listDTO, relatorioUtil.caminhoArquivoLancamentos() , relatorioUtil.caminhoMapaDeLogos() );	
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Erro ao gerar pdf: " + e.getMessage());
		}		
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/tipos")
	public Collection<TipoLancamentoEnum> tipos() {
		return Arrays.asList(TipoLancamentoEnum.values());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/status")
	public Collection<StatusLancamento> status() {
		return Arrays.asList(StatusLancamento.values());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/categorias")
	public Collection<CategoriaEnum> categorias() {
		return Arrays.asList(CategoriaEnum.DIVERSOS);
	}
	
	@GetMapping(value="/estatistica")
	public ResponseEntity<SaldoLancamentoDTO> estatistica() {
		SaldoLancamentoDTO saldo = lancamentoService.estatistica();
		return ResponseEntity.ok().body(saldo);
	}
}
