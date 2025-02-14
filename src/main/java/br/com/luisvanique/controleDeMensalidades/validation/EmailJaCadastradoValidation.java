package br.com.luisvanique.controleDeMensalidades.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.exception.EmailJaCadastradoException;
import br.com.luisvanique.controleDeMensalidades.exception.TelefoneJaCadastradoException;
import br.com.luisvanique.controleDeMensalidades.model.Instrutor;
import br.com.luisvanique.controleDeMensalidades.repository.InstrutorRepository;

@Component
public class EmailJaCadastradoValidation implements ICreateInstrutorValidator, IUpdateInstrutorValidator{
	
	@Autowired
	private InstrutorRepository instrutorRepository;

	@Override
	public void validator(InstrutorDto dto) {
		if(instrutorRepository.existsByEmail(dto.email())) {
			throw new EmailJaCadastradoException("Email Ja cadastrado no sistema!");
		}
	}
	
	@Override
	public void validator(InstrutorDto instrutorDto, Instrutor instrutor) {
		if (!instrutorDto.email().equals(instrutor.getEmail()) 
				&& instrutorRepository.existsByEmail(instrutorDto.email())) 
			throw new EmailJaCadastradoException("O Email já foi cadastrado no sistema!");
		
		if (!instrutorDto.telefone().equals(instrutor.getTelefone()) 
				&& instrutorRepository.existsByTelefone(instrutorDto.telefone())) 
			throw new TelefoneJaCadastradoException("O Telefone já foi cadastrado no sistema!");
	}


}
