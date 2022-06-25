package br.com.app.infraestructure.exception;

import java.io.Serializable;

public class MensagemException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensagemException(String msg)
	{
		super(msg);
	}

}
