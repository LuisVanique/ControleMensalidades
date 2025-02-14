package br.com.luisvanique.controleDeMensalidades.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.exception.TelefoneJaCadastradoException;
import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.model.Instrutor;
import br.com.luisvanique.controleDeMensalidades.repository.AlunoRepository;
import br.com.luisvanique.controleDeMensalidades.repository.InstrutorRepository;

@Component
public class TelefoneJaRegistradoValidation implements ICreateAlunoValidator, IUpdateAlunoValidator, ICreateInstrutorValidator, IUpdateInstrutorValidator {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private InstrutorRepository instrutorRepository;

	@Override
	public void validator(AlunoDTO dto) {
		if (alunoRepository.existsByTelefone(dto.telefone()))
			throw new TelefoneJaCadastradoException("O Telefone já foi registrado no sistema!");
	}

	@Override
	public void validator(AlunoDTO alunoDTO, Aluno alunoAtual) {
		if (!alunoDTO.telefone().equals(alunoAtual.getTelefone()) 
				&& alunoRepository.existsByTelefone(alunoDTO.telefone())) 
			throw new TelefoneJaCadastradoException("O Telefone já foi cadastrado no sistema!");
	}

	@Override
	public void validator(InstrutorDto dto) {
		if (instrutorRepository.existsByTelefone(dto.telefone()))
			throw new TelefoneJaCadastradoException("O Telefone já foi registrado no sistema!");
	}
	
	@Override
	public void validator(InstrutorDto instrutorDto, Instrutor instrutor) {
		if (!instrutorDto.telefone().equals(instrutor.getTelefone()) 
				&& alunoRepository.existsByTelefone(instrutorDto.telefone())) 
			throw new TelefoneJaCadastradoException("O Telefone já foi cadastrado no sistema!");
	}
}
