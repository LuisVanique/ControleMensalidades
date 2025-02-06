package br.com.luisvanique.controleDeMensalidades.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Pessoa {
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "telefone", nullable = false, unique = true)
	private String telefone;

	private LocalDate dataCriacao = LocalDate.now();
	
	public Pessoa(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Pessoa() {
		
	}
	
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
