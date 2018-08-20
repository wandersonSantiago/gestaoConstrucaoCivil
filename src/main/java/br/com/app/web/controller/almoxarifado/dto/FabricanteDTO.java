package br.com.app.web.controller.almoxarifado.dto;

import br.com.app.web.controller.dto.DadoEmpresaDTO;
import lombok.Data;

@Data
public class FabricanteDTO {

	private Long id;
	private String contato;
	private String observacao;
	private DadoEmpresaDTO dadoEmpresa;
}
