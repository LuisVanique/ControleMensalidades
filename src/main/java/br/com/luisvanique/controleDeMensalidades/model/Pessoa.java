package br.com.luisvanique.controleDeMensalidades.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Pessoa {
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	private LocalDate dataCriacao = LocalDate.now();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
}
