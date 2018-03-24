package br.com.system.gestaoConstrucaoCivil.web.controller.chamado;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/lista")
	public Collection<ChamadoManutencao> lista() {
		return chamadoManutencaoService.listaSuporte();

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/relatorio/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
	public Collection<ChamadoManutencao> relatorio(@PathVariable Date dataInicial, @PathVariable Date dataFinal) {
		return chamadoManutencaoService.relatorio(dataInicial, dataFinal);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/relatorio/dataInicial/{dataInicial}/dataFinal/{dataFinal}/titulo/{titulo}")
	public Collection<ChamadoManutencao> relatorioPorDataETitulo(@PathVariable Date dataInicial,
			@PathVariable Date dataFinal, @PathVariable String titulo) {

		return chamadoManutencaoService.relatorioPorDataETitulo(dataInicial, dataFinal, titulo);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/relatorio")
	public Page<ChamadoManutencao> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return chamadoManutencaoService.buscarPoEmpreendimentoComPaginacao(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/usuario/lista")
	public Collection<ChamadoManutencao> listaUsuario() {
		return chamadoManutencaoService.listaChamadoManutencaoUsuario();

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salvar")
	public void salvar(@RequestBody ChamadoManutencao chamadoManutencao) {

		chamadoManutencaoService.salvarEditar(chamadoManutencao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/servicos")
	public void servicos(@RequestBody ChamadoManutencao chamadoManutencao) {
		chamadoManutencaoService.servicos(chamadoManutencao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/mensagem")
	public void alterar(@RequestBody ChamadoManutencao chamadoManutencao) {

		chamadoManutencaoService.mensagens(chamadoManutencao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/atender")
	public void atender(@RequestBody ChamadoManutencao chamadoManutencao) {
		chamadoManutencaoService.atenderChamado(chamadoManutencao);
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/fechar")
	public void fechar(@RequestBody ChamadoManutencao chamadoManutencao) {
		chamadoManutencaoService.fecharChamado(chamadoManutencao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/silenciar/false")
	public void silenciarFalse(@RequestBody ChamadoManutencao chamadoManutencao) {
		chamadoManutencaoService.silenciarChamadoFalse(chamadoManutencao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/silenciar/true")
	public void silenciarTrue(@RequestBody ChamadoManutencao chamadoManutencao) {
		chamadoManutencaoService.silenciarChamadoTrue(chamadoManutencao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public ChamadoManutencao buscarPorId(@PathVariable Long id) {
		return chamadoManutencaoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prioridade")
	public Collection<PrioridadeChamado> prioridade() {
		return Arrays.asList(PrioridadeChamado.values());

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/status")
	public Collection<StatusChamado> status() {
		return Arrays.asList(StatusChamado.values());

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/titulo")
	public Collection<TituloChamadoManutencaoEnum> titulo() {
		return Arrays.asList(TituloChamadoManutencaoEnum.values());

	}

}
