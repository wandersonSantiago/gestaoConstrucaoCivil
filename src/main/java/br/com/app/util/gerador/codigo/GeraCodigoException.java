package br.com.app.util.gerador.codigo;

import java.io.Serializable;

public class GeraCodigoProdutoException extends RuntimeException implements Serializable{

	 
	private static final long serialVersionUID = 1L;

	public GeraCodigoProdutoException(String msg) {
		 super(msg);
	}
}
