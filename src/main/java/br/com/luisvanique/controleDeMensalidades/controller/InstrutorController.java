package br.com.luisvanique.controleDeMensalidades.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.model.Instrutor;
import br.com.luisvanique.controleDeMensalidades.service.InstrutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	
	@Autowired
	private InstrutorService instrutorService;

	@PostMapping
	public ResponseEntity<InstrutorDto> create(@RequestBody @Valid InstrutorDto dto){
		Instrutor instrutor = instrutorService.create(dto);
		InstrutorDto instrutorDto = new InstrutorDto(instrutor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(instrutorDto.id()).toUri();
		return ResponseEntity.created(uri).body(instrutorDto);
	}
	
	@GetMapping
	public ResponseEntity<Page<InstrutorDto>> findAll(@PageableDefault Pageable pageable){
		Page<Instrutor> alunos = instrutorService.findAll(pageable);
		Page<InstrutorDto> instrutorDto = alunos.map(InstrutorDto::new);
		return ResponseEntity.ok().body(instrutorDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InstrutorDto> findById(@PathVariable Long id){
		Instrutor instrutor = instrutorService.findById(id);
		InstrutorDto dto = new InstrutorDto(instrutor);
		return ResponseEntity.ok().body(dto);
	}
	
}
