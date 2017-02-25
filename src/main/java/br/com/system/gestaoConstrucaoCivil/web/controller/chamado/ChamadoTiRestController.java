package br.com.system.gestaoConstrucaoCivil.web.controller.chamado;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.chamado.ChamadoTi;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.PrioridadeChamado;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.StatusChamado;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.TipoEquipamentoChamadoTi;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.TituloChamadoImpressoraEnum;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.TituloChamadoTiEnum;
import br.com.system.gestaoConstrucaoCivil.service.chamado.ChamadoTiService;

@RestController
@RequestMapping("/rest/chamado/chamadoTi")
public class ChamadoTiRestController {

	@Autowired
	private ChamadoTiService chamadoTiService;

	@RequestMapping(method = RequestMethod.GET, value = "/suporte/lista")
	public ResponseEntity<Iterable<ChamadoTi>> listaSuporte() {
		Iterable<ChamadoTi> chamadoTi = chamadoTiService.listaSuporte();
		return new ResponseEntity<Iterable<ChamadoTi>>(chamadoTi, HttpStatus.OK);
	}

	@GetMapping(value = "/suporte/relatorio/data/{dataInicial,dataFinal}")
	public ResponseEntity<Iterable<ChamadoTi>>relatorio(Date dataInicial, Date dataFinal) {
		
			Iterable<ChamadoTi> chamadoTi = chamadoTiService.relatorio(dataInicial,dataFinal);
			return new ResponseEntity<Iterable<ChamadoTi>>(chamadoTi, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/usuario/lista")
	public ResponseEntity<Iterable<ChamadoTi>> listaUsuario() {
		Iterable<ChamadoTi> chamadoTi = chamadoTiService.listaChamadoTiUsuario();
		return new ResponseEntity<Iterable<ChamadoTi>>(chamadoTi, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ResponseEntity<ChamadoTi> salvar(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.salvarEditar(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/servicos")
	public ResponseEntity<ChamadoTi> servicos(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.servicos(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/alterar")
	public ResponseEntity<ChamadoTi> alterar(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.salvarEditar(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<ChamadoTi> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ChamadoTi>(chamadoTiService.buscaPorId(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/prioridade")
	public ResponseEntity<Iterable<PrioridadeChamado>> prioridade() {
		Iterable<PrioridadeChamado> prioridadeChamado = Arrays.asList(PrioridadeChamado.values());
		return new ResponseEntity<Iterable<PrioridadeChamado>>(prioridadeChamado, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/mensagem")
	public ResponseEntity<ChamadoTi> mensagem(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.mensagens(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/atender")
	public ResponseEntity<ChamadoTi> atender(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.atenderChamado(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/fechar")
	public ResponseEntity<ChamadoTi> fechar(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.fecharChamado(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/silenciar/false")
	public ResponseEntity<ChamadoTi> silenciarFalse(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.silenciarChamadoFalse(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/silenciar/true")
	public ResponseEntity<ChamadoTi> silenciarTrue(@RequestBody ChamadoTi chamadoTi, UriComponentsBuilder ucBuilder) {
		chamadoTiService.silenciarChamadoTrue(chamadoTi);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public ResponseEntity<Iterable<StatusChamado>> status() {
		Iterable<StatusChamado> statusChamado = Arrays.asList(StatusChamado.values());
		return new ResponseEntity<Iterable<StatusChamado>>(statusChamado, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/equipamento/tipo")
	public ResponseEntity<Iterable<TipoEquipamentoChamadoTi>> tipoEquipamentoChamado() {
		Iterable<TipoEquipamentoChamadoTi> tipoEquipamentoChamadoTi = Arrays.asList(TipoEquipamentoChamadoTi.values());
		return new ResponseEntity<Iterable<TipoEquipamentoChamadoTi>>(tipoEquipamentoChamadoTi, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/titulo/tI")
	public ResponseEntity<Iterable<TituloChamadoTiEnum>> tituloChamadoInformatica() {
		Iterable<TituloChamadoTiEnum> tituloChamadoTiEnum = Arrays.asList(TituloChamadoTiEnum.values());
		return new ResponseEntity<Iterable<TituloChamadoTiEnum>>(tituloChamadoTiEnum, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/titulo/impressora")
	public ResponseEntity<Iterable<TituloChamadoImpressoraEnum>> tituloChamadoImpressora() {
		Iterable<TituloChamadoImpressoraEnum> tituloChamadoImpressoraEnum = Arrays
				.asList(TituloChamadoImpressoraEnum.values());
		return new ResponseEntity<Iterable<TituloChamadoImpressoraEnum>>(tituloChamadoImpressoraEnum, HttpStatus.OK);
	}
}
