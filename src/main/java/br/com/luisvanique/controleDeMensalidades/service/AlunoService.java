package br.com.luisvanique.controleDeMensalidades.service;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import br.com.luisvanique.controleDeMensalidades.exception.ObjectNotFoundException;
import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.repository.AlunoRepository;
import br.com.luisvanique.controleDeMensalidades.validation.ICreateUserValidator;
import br.com.luisvanique.controleDeMensalidades.validation.IUpdateUserValidator;
import jakarta.transaction.Transactional;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private List<ICreateUserValidator> createUserValidator;
	
	@Autowired
	private List<IUpdateUserValidator> updateUserValidator;
	
	@Transactional
	public Aluno create(AlunoDTO alunoDto) {
		createUserValidator.forEach(validator -> validator.validator(alunoDto));

		Aluno aluno = new Aluno(alunoDto);
		alunoRepository.save(aluno);
		return aluno;
	}

	public Page<Aluno> findAll(Pageable pageable) {
		return alunoRepository.findAll(pageable);
	}
	
	public Aluno findById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado"));
	}

	@Transactional
	public void delete(Long id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno Não encontrado"));
		aluno.setAtivo("N");
		alunoRepository.save(aluno);
	}

	@Transactional
	public void update(Long id, AlunoDTO alunoDTO) {
		Aluno alunoAtual = alunoRepository.findById(id)
		        .orElseThrow(() -> new ObjectNotFoundException("Aluno Não encontrado"));
		
		updateUserValidator.forEach(validator -> validator.validator(alunoDTO, alunoAtual));
	    BeanUtils.copyProperties(alunoDTO.endereco(), alunoAtual.getEndereco());
	    BeanUtils.copyProperties(alunoDTO, alunoAtual, "id");

	    alunoRepository.save(alunoAtual);
	}
}
