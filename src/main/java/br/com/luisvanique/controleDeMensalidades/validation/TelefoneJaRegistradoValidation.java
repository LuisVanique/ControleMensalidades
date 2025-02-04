package br.com.luisvanique.controleDeMensalidades.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import br.com.luisvanique.controleDeMensalidades.exception.TelefoneJaCadastradoException;
import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.repository.AlunoRepository;

@Component
public class TelefoneJaRegistradoValidation implements ICreateUserValidator, IUpdateUserValidator {

	@Autowired
	private AlunoRepository repository;

	@Override
	public void validator(AlunoDTO dto) {
		if (repository.existsByTelefone(dto.telefone()))
			throw new TelefoneJaCadastradoException("O Telefone já foi registrado no sistema!");
	}

	@Override
	public void validator(AlunoDTO alunoDTO, Aluno alunoAtual) {
		if (!alunoDTO.telefone().equals(alunoAtual.getTelefone()) 
				&& repository.existsByTelefone(alunoDTO.telefone())) 
			throw new TelefoneJaCadastradoException("O Telefone já foi cadastrado no sistema!");
	}
}
