package br.com.app.web.controller.dto;

import java.util.Date;

import br.com.app.enuns.StatusEmpreendimento;
import lombok.Data;

@Data
public class EmpreendimentoDTO {

	private Long id;
	private String descricao;
	private String telefone;
	private Double valorMaximoGastar;
	private Double valoresGastos = 0.0;
	private Double porcentagem =  0.0;
	private Date dataAbertura;
	private Date datafechamento;
	private StatusEmpreendimento status;
}
