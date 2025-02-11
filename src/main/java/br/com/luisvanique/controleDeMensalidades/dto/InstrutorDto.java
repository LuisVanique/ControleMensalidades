package br.com.luisvanique.controleDeMensalidades.dto;

import br.com.luisvanique.controleDeMensalidades.model.Instrutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InstrutorDto(
		Long id,
		
		@NotBlank(message = "Campo Nome Obrigatorio!")
		@NotNull(message = "Campo Nome Obrigatorio!")
		String nome,
		
		@NotBlank(message = "Campo Login Obrigatorio!")
		@NotNull(message = "Campo Login Obrigatorio!")
		String login,
		
		@NotBlank(message = "Campo senha Obrigatorio!")
		@NotNull(message = "Campo senha Obrigatorio!")
		String senha,
		
		@NotBlank(message = "Campo email Obrigatorio!")
		@NotNull(message = "Campo email Obrigatorio!")
		String email,
		
		@NotBlank(message = "Campo telefone Obrigatorio!")
		@NotNull(message = "Campo telefone Obrigatorio!")
		String telefone
		
		) {
	
	public InstrutorDto(Instrutor instrutor) {
		this(instrutor.getId(), instrutor.getNome(),instrutor.getLogin(), instrutor.getSenha(), instrutor.getEmail(), instrutor.getTelefone());
	}

}
