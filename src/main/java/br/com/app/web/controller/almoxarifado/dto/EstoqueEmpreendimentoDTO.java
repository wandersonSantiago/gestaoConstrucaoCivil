package br.com.app.web.controller.almoxarifado.dto;

import br.com.app.entity.almoxarifado.Produto;
import br.com.app.web.controller.dto.EmpreendimentoDTO;
import lombok.Data;

@Data
public class EstoqueEmpreendimentoDTO {

	private Long id;
	private Integer quantidade;
	private String localizacao;
	private EmpreendimentoDTO empreendimento;
	private Produto produto;
	private Integer quantidadeMaxima;
	private Integer quantidadeMinima1;
	private Double custoMedio;
	private Double valorTotal;
	
	
}
