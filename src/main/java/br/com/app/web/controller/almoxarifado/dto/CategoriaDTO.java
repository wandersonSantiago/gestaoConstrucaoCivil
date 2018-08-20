package br.com.app.web.controller.almoxarifado.dto;

import lombok.Data;

@Data
public class CategoriaDTO {

	private Long id;
	private boolean ativo;
	private String descricao;
	private CategoriaDTO categoria;
}
