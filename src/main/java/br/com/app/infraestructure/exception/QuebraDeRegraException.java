package br.com.app.infraestructure.exception;



public class QuebraDeRegraException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuebraDeRegraException() {
		super();
	}

	public QuebraDeRegraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QuebraDeRegraException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuebraDeRegraException(String message) {
		super(message);
	}

	public QuebraDeRegraException(Throwable cause) {
		super(cause);
	}

}
