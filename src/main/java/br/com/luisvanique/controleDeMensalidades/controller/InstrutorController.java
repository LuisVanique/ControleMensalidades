package br.com.luisvanique.controleDeMensalidades.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
}
