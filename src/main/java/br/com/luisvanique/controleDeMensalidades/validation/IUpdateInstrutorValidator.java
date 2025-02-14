package br.com.luisvanique.controleDeMensalidades.validation;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.model.Instrutor;

public interface IUpdateInstrutorValidator {
	
	public void validator(InstrutorDto instrutorDto, Instrutor instrutor);
	
}
