package br.com.luisvanique.controleDeMensalidades.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.exception.EmailJaCadastradoException;
import br.com.luisvanique.controleDeMensalidades.repository.InstrutorRepository;

@Component
public class EmailJaCadastradoValidation implements ICreateInstrutorValidator{
	
	@Autowired
	private InstrutorRepository instrutorRepository;

	@Override
	public void validator(InstrutorDto dto) {
		if(instrutorRepository.existsByEmail(dto.email())) {
			throw new EmailJaCadastradoException("Email Ja cadastrado no sistema!");
		}
		
	}

}
