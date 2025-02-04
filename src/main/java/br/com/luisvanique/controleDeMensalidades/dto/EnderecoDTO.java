package br.com.luisvanique.controleDeMensalidades.dto;

import br.com.luisvanique.controleDeMensalidades.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
		@NotBlank(message = "Cep não pode estar vazio!")
		@NotNull(message = "Cep nao pode estar Nulo")
		String cep,
		
		@NotBlank(message = "logradouro não pode estar vazio!")
		@NotNull(message = "logradouro nao pode estar Nulo")
		String logradouro,
		
		@NotBlank(message = "bairro não pode estar vazio!")
		@NotNull(message = "bairro nao pode estar Nulo")
		String bairro
		) {

		public EnderecoDTO(Endereco endereco) {
			this(endereco.getCep(), endereco.getLogradouro(), endereco.getBairro());
		}

		public Endereco toEndereco() {
			return new Endereco(cep, logradouro, bairro);
		}

}
