package br.com.app.web.controller.almoxarifado.dto;

import java.util.List;

import br.com.app.enuns.UnidadeMedidaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class ProdutoDTO {

	private Long id;
	private Integer codigo;
	private String codigoBarra;
	private String descricao;
	private boolean ativo;
	private UnidadeMedidaEnum unidadeMedida;
	private String categoria;
	private List<String> fornecedores;
	private String fabricante;
	private String tipoProduto;
	
	public ProdutoDTO() {
		
	}
}
