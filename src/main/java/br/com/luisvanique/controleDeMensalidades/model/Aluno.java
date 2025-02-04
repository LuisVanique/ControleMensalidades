package br.com.luisvanique.controleDeMensalidades.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_alunos")
public class Aluno extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Embedded
	@Column(nullable = false)
	private Endereco endereco;

	@Column(name = "telefone", nullable = false, unique = true)
	private String telefone;

	@Column(name = "ativo", nullable = false)
	private String ativo = "S";

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "aluno")
	private List<Mensalidade> mensalidades = new ArrayList<>();
	
	public Aluno() {
		
	}

	public Aluno(String nome, Endereco endereco, String telefone, LocalDate dataNascimento) {
		super(nome);
		this.endereco = endereco;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public Aluno(AlunoDTO alunoDto) {
		super(alunoDto.nome());
		this.endereco = alunoDto.endereco().toEndereco();
		this.telefone = alunoDto.telefone();
		this.dataNascimento = alunoDto.dataNascimento();
	}
	
	public Long getId() {
		return id;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<Mensalidade> getMensalidades() {
		return mensalidades;
	}

	public void setMensalidades(List<Mensalidade> mensalidades) {
		this.mensalidades = mensalidades;
	}

}
