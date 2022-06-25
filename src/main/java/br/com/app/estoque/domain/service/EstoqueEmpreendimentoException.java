package br.com.app.estoque.domain.service;

import java.io.Serializable;

public class EstoqueEmpreendimentoException extends RuntimeException implements Serializable {

	
	private static final long serialVersionUID = 1L;

	EstoqueEmpreendimentoException(String msg)
	{
		super(msg);
	}
}
