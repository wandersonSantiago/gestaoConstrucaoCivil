package br.com.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CorpoPdfDTO {

	private String titulo;
	private Date dataInicial;
	private Date dataFinal;
	private Date observacao;
	private List<?> itens = new ArrayList<>();
	
	public CorpoPdfDTO(String titulo, List<?> itens) {
		super();
		this.titulo = titulo;
		this.itens = itens;
	}
	
	
	
}
