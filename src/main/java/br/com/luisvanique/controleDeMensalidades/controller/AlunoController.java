package br.com.luisvanique.controleDeMensalidades.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	

	@GetMapping
	public ResponseEntity<Page<AlunoDTO>> findAll(@PageableDefault Pageable pageable){
		Page<Aluno> alunos = alunoService.findAll(pageable);
		Page<AlunoDTO> alunosDTO = alunos.map(AlunoDTO::new);
		return ResponseEntity.ok().body(alunosDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> findById(@PathVariable Long id){
		Aluno aluno = alunoService.findById(id);
		AlunoDTO dto = new AlunoDTO(aluno);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping 
	public ResponseEntity<AlunoDTO> create(@RequestBody @Valid AlunoDTO alunoDTO){
		Aluno aluno = alunoService.create(alunoDTO);
		AlunoDTO dtoAluno = new AlunoDTO(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(alunoDTO.id()).toUri();
		return ResponseEntity.created(uri).body(dtoAluno);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid AlunoDTO alunoDTO){
		alunoService.update(id, alunoDTO);
		return ResponseEntity.ok().build();
	}
	
	
}
