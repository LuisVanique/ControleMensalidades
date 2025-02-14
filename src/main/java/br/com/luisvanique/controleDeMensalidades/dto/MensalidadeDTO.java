package br.com.luisvanique.controleDeMensalidades.dto;

import java.time.LocalDate;

import br.com.luisvanique.controleDeMensalidades.model.Mensalidade;

public record MensalidadeDTO(
		AlunoDTO aluno,
		
		LocalDate dataVencimento,
		
		Double valor,
		
		Integer status) {

	public MensalidadeDTO(Mensalidade mensalidade) {
		this(new AlunoDTO(mensalidade.getAluno()), mensalidade.getDataVencimento(), mensalidade.getValor(),
				mensalidade.getStatus());
	}
}