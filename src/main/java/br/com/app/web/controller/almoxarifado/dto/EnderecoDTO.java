package br.com.app.web.controller.almoxarifado.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

	private Long id;
	
	private String logradouro;
	private String bairro;
	private String localidade;
	private Integer numero;
	private String uf;
	private String cep;
	private String complemento;
}
