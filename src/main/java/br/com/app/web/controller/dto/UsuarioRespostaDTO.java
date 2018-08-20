package br.com.app.web.controller.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioRespostaDTO {

	private Long id;
	private String empreendimento;
	private String nome;
	private String login;
	private String email;
	private boolean ativo;
	private String  caminhoFoto;
    private LocalDate dataCadastro;
    private String status;
}
