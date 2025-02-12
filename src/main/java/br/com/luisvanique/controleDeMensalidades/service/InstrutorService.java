package br.com.luisvanique.controleDeMensalidades.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.exception.ObjectNotFoundException;
import br.com.luisvanique.controleDeMensalidades.model.Instrutor;
import br.com.luisvanique.controleDeMensalidades.repository.InstrutorRepository;
import br.com.luisvanique.controleDeMensalidades.validation.ICreateInstrutorValidator;
import jakarta.transaction.Transactional;

@Service
public class InstrutorService {
	
	@Autowired
	private InstrutorRepository repository;
	
	@Autowired
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	private List<ICreateInstrutorValidator> validator;

	@Transactional
	public Instrutor create(InstrutorDto instrutorDto) {
		validator.forEach(validator -> validator.validator(instrutorDto));
		Instrutor instrutor = new Instrutor(instrutorDto);
		instrutor.setSenha(encoder.encode(instrutorDto.senha()));
		repository.save(instrutor);
		return instrutor;
	}
	
	public Page<Instrutor> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Instrutor findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Instrutor não encontrado"));
	}

	
}
