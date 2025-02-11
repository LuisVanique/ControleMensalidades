package br.com.luisvanique.controleDeMensalidades.validation;

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import br.com.luisvanique.controleDeMensalidades.model.Aluno;

public interface IUpdateAlunoValidator {

	public void validator(AlunoDTO dto, Aluno aluno);
	
}
