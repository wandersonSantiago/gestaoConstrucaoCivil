package br.com.app.web.controller.almoxarifado;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.enuns.StatusCotacao;
import br.com.app.exceptions.NotFoundException;
import br.com.app.jasper.JasperReportsService;
import br.com.app.jasper.RelatorioUtil;
import br.com.app.repository.filter.CotacaoFilter;
import br.com.app.service.almoxarifado.CotacaoService;

@RestController
@RequestMapping("/rest/almoxarifado/cotacao")
public class CotacaoRestController {

	@Autowired
	private CotacaoService cotacaoService;
	@Autowired
	private JasperReportsService jasperReportsService;
	@Autowired
	private RelatorioUtil relatorioUtil;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody Cotacao cotacao) {
		cotacaoService.salvaAltera(cotacao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<Cotacao> buscarPorId(@PathVariable Long id) {
		return cotacaoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/close/{idCotacao}")
	public void fecharCotacao(@PathVariable Long idCotacao) {
		cotacaoService.fecharCotacao(idCotacao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/status")
	public Collection<StatusCotacao> status() {
		return Arrays.asList(StatusCotacao.values());
	}
	
	@PostMapping(value = "/filter")
	public ResponseEntity<Page<Cotacao>> filtro(@RequestBody CotacaoFilter cotacaoFilter) {

		Page<Cotacao> list = cotacaoService.pageFilter(cotacaoFilter, PageRequest.of(
				cotacaoFilter.getPage().getPage(), cotacaoFilter.getPage().getLinesPerPage(), Direction.valueOf(cotacaoFilter.getPage().getDirection()), cotacaoFilter.getPage().getOrderBy()));
		
		return ResponseEntity.ok().body(list);
	}
	
	
	@PostMapping(value = "/imprimir")
	@ResponseBody
	public byte[] filtroPdf(@RequestBody CotacaoFilter cotacaoFilter, HttpServletResponse response) {
		
		response.setHeader("Content-Disposition", "inline; filename=file.pdf");
	    response.setContentType("application/pdf");
	    
	    List<Cotacao> cotacoes = (List<Cotacao>) cotacaoService.listFilter(cotacaoFilter);
	try {	
	
		return jasperReportsService.generateReport(cotacoes, relatorioUtil.caminhoArquivoCotacao() , relatorioUtil.caminhoMapaDeLogos(new HashMap<>()) );	
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Erro ao gerar pdf: " + e.getMessage());
		}		
		
	}
}
