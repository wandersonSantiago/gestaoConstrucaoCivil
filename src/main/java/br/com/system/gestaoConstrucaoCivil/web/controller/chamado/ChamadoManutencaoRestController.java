package br.com.system.gestaoConstrucaoCivil.web.controller.chamado;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.chamado.ChamadoManutencao;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.PrioridadeChamado;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.StatusChamado;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.TituloChamadoManutencaoEnum;
import br.com.system.gestaoConstrucaoCivil.service.chamado.ChamadoManutencaoService;

@RestController
@RequestMapping("/rest/chamado/chamadoManutencao")
public class ChamadoManutencaoRestController {

	
	@Autowired
	private ChamadoManutencaoService chamadoManutencaoService;
	
	
	 @RequestMapping(method = RequestMethod.GET, value="/suporte/lista")
	 public ResponseEntity<Iterable<ChamadoManutencao>> lista() {	  
	  Iterable<ChamadoManutencao> chamadoManutencao = chamadoManutencaoService.listaSuporte();
	  return new ResponseEntity<Iterable<ChamadoManutencao>>(chamadoManutencao, HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/suporte/relatorio")
		public ResponseEntity<Page<ChamadoManutencao>> relatorio(@RequestParam(defaultValue="0", required=false) int page
				,@RequestParam(defaultValue="0", required=false) int maxResults) {
			Page<ChamadoManutencao> chamadoManutencao = chamadoManutencaoService.relatorio(new PageRequest(page, maxResults));
			return new ResponseEntity<Page<ChamadoManutencao>>(chamadoManutencao, HttpStatus.OK);
		}
	 
	 @RequestMapping(method = RequestMethod.GET, value="/usuario/lista")
	 public ResponseEntity<Iterable<ChamadoManutencao>> listaUsuario() {	  
	  Iterable<ChamadoManutencao> chamadoManutencao = chamadoManutencaoService.listaChamadoManutencaoUsuario();
	  return new ResponseEntity<Iterable<ChamadoManutencao>>(chamadoManutencao, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<ChamadoManutencao> salvar(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.salvarEditar(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }
	 @RequestMapping(method = RequestMethod.PUT, value="/servicos")
	 public ResponseEntity<ChamadoManutencao> servicos(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.servicos(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/mensagem")
	 public ResponseEntity<ChamadoManutencao> alterar(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.mensagens(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/atender")
	 public ResponseEntity<ChamadoManutencao> atender(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.atenderChamado(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/fechar")
	 public ResponseEntity<ChamadoManutencao> fechar(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.fecharChamado(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/silenciar/false")
	 public ResponseEntity<ChamadoManutencao> silenciarFalse(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.silenciarChamadoFalse(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }
	 @RequestMapping(method = RequestMethod.PUT, value="/silenciar/true")
	 public ResponseEntity<ChamadoManutencao> silenciarTrue(@RequestBody ChamadoManutencao chamadoManutencao,UriComponentsBuilder ucBuilder){
		 chamadoManutencaoService.silenciarChamadoTrue(chamadoManutencao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoManutencao>(headers, HttpStatus.CREATED);
	 }
	
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<ChamadoManutencao> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ChamadoManutencao>(chamadoManutencaoService.buscaPorId(id), HttpStatus.OK);
		}
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/prioridade")
		public ResponseEntity<Iterable<PrioridadeChamado>> prioridade() {
			Iterable<PrioridadeChamado> prioridadeChamado = Arrays.asList(PrioridadeChamado.values());
			return new ResponseEntity<Iterable<PrioridadeChamado>>(prioridadeChamado, HttpStatus.OK);
		}
	 @RequestMapping(method = RequestMethod.GET, value = "/status")
		public ResponseEntity<Iterable<StatusChamado>> status() {
			Iterable<StatusChamado> statusChamado = Arrays.asList(StatusChamado.values());
			return new ResponseEntity<Iterable<StatusChamado>>(statusChamado, HttpStatus.OK);
		}
	 @RequestMapping(method = RequestMethod.GET, value = "/titulo")
		public ResponseEntity<Iterable<TituloChamadoManutencaoEnum>> titulo() {
			Iterable<TituloChamadoManutencaoEnum> tituloChamadoManutencaoEnum = Arrays.asList(TituloChamadoManutencaoEnum.values());
			return new ResponseEntity<Iterable<TituloChamadoManutencaoEnum>>(tituloChamadoManutencaoEnum, HttpStatus.OK);
		}

}
