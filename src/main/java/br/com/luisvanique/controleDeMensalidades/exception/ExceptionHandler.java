package br.com.luisvanique.controleDeMensalidades.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.luisvanique.controleDeMensalidades.dto.MessageErrorDTO;



@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(TelefoneJaCadastradoException.class)
	public ResponseEntity<MessageErrorDTO> telefoneRegistradoMsg(TelefoneJaCadastradoException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(new MessageErrorDTO(ex.getMessage()));
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<MessageErrorDTO> objetoNaoEncontrado(ObjectNotFoundException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON).body(new MessageErrorDTO(ex.getMessage()));
	}
}
