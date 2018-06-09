package br.com.app.util.gerador.codigo;

import java.io.Serializable;

public class GeraCodigoException extends RuntimeException implements Serializable{

	 
	private static final long serialVersionUID = 1L;

	public GeraCodigoException(String msg) {
		 super(msg);
	}
}
