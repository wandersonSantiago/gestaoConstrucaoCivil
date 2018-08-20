package br.com.app.web.controller.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.app.enuns.UfEnum;
import br.com.app.web.controller.almoxarifado.dto.EnderecoDTO;
import lombok.Data;

@Data
public class DadoEmpresaDTO {

	private Long id;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	@Enumerated(EnumType.STRING)
	private UfEnum ufIe;
	private String inscricaoEstadual;
	private EnderecoDTO endereco;
	private String telefone;
	private String email;
	private String site;
	private Integer status;
}
