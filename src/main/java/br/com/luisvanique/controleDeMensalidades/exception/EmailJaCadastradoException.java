package br.com.luisvanique.controleDeMensalidades.exception;

public class EmailJaCadastradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EmailJaCadastradoException(String msg) {
		super(msg);
	}

}
