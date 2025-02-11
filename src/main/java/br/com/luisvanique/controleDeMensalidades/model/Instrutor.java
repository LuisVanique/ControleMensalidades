package br.com.luisvanique.controleDeMensalidades.model;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_instrutores")
public class Instrutor extends Pessoa{
	
	public Instrutor(String nome, String login, String email,String senha, String telefone) {
		super(nome, telefone);
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public Instrutor(InstrutorDto instrutorDto) {
		super(instrutorDto.nome(), instrutorDto.telefone());
		this.login = instrutorDto.login();
		this.senha = instrutorDto.senha();
		this.email = instrutorDto.email();
	}
	
	public Instrutor() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "email")
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
