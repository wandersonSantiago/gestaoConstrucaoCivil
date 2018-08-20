package br.com.app.web.controller.almoxarifado.dto;

import lombok.Data;

@Data
public class TipoProdutoDTO {

	private Long id;
	private boolean ativo;
	private String descricao;
}
