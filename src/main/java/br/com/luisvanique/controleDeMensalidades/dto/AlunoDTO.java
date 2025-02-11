package br.com.luisvanique.controleDeMensalidades.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
		
		Long id,
		
		@NotBlank(message = "Nome nao pode estar branco!")
		@NotNull(message = "Nome nao pode estar nulo!")
		String nome,
		
		@NotBlank(message = "Nome nao pode estar branco!")
		@NotNull(message = "Nome nao pode estar nulo!")
		String telefone, 
		
		@NotBlank(message = "Nome nao pode estar branco!")
		@NotNull(message = "Nome nao pode estar nulo!")
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		LocalDate dataNascimento,
	
		@Valid
		EnderecoDTO endereco,
		
		String ativo
		) {

	public AlunoDTO(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), new EnderecoDTO(aluno.getEndereco()),  aluno.getAtivo());
	}
		
		
}

