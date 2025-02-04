package br.com.luisvanique.controleDeMensalidades.exception;

public class TelefoneJaCadastradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TelefoneJaCadastradoException(String msg) {
		super(msg);
	}
}
