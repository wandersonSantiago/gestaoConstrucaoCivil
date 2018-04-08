package br.com.system.gestaoConstrucaoCivil.web.controller.chamado;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/lista")
	public Collection<ChamadoTi> listaSuporte() {
		return chamadoTiService.listaSuporte();
		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/relatorio/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
	public Collection<ChamadoTi> relatorio(@PathVariable Date dataInicial, @PathVariable Date dataFinal) {
		return chamadoTiService.relatorio(dataInicial, dataFinal);
		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/relatorio/dataInicial/{dataInicial}/dataFinal/{dataFinal}/titulo/{titulo}")
	public Collection<ChamadoTi> relatorioPorDataETitulo(@PathVariable Date dataInicial, @PathVariable Date dataFinal,
			@PathVariable String titulo) {
		return chamadoTiService.relatorioPorDataETitulo(dataInicial, dataFinal, titulo);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/suporte/relatorio")
	public Page<ChamadoTi> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return chamadoTiService.buscarPoEmpreendimentoComPaginacao(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/usuario/lista")
	public Collection<ChamadoTi> listaUsuario() {
		return chamadoTiService.listaChamadoTiUsuario();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salvar")
	public void salvar(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.salvarEditar(chamadoTi);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/servicos")
	public void servicos(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.servicos(chamadoTi);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/alterar")
	public void alterar(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.salvarEditar(chamadoTi);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<ChamadoTi> buscarPorId(@PathVariable Long id) {
		return chamadoTiService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prioridade")
	public Collection<PrioridadeChamado> prioridade() {
		return Arrays.asList(PrioridadeChamado.values());

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/mensagem")
	public void mensagem(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.mensagens(chamadoTi);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/atender")
	public void atender(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.atenderChamado(chamadoTi);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/fechar")
	public void fechar(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.fecharChamado(chamadoTi);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/silenciar/false")
	public void silenciarFalse(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.silenciarChamadoFalse(chamadoTi);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/silenciar/true")
	public void silenciarTrue(@RequestBody ChamadoTi chamadoTi) {
		chamadoTiService.silenciarChamadoTrue(chamadoTi);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/status")
	public Collection<StatusChamado> status() {
		return Arrays.asList(StatusChamado.values());

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/equipamento/tipo")
	public Collection<TipoEquipamentoChamadoTi> tipoEquipamentoChamado() {
		return Arrays.asList(TipoEquipamentoChamadoTi.values());

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/titulo/tI")
	public Collection<TituloChamadoTiEnum> tituloChamadoInformatica() {
		return Arrays.asList(TituloChamadoTiEnum.values());

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/titulo/impressora")
	public Collection<TituloChamadoImpressoraEnum> tituloChamadoImpressora() {
		return Arrays.asList(TituloChamadoImpressoraEnum.values());

	}
}
