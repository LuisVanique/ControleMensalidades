package br.com.luisvanique.controleDeMensalidades.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mensalidades")
public class Mensalidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Aluno aluno;
	
	@Column(name = "valor")
	private String valor;
	
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	
	@Column(name = "status")
	private String statusMensalidade = StatusMensalidade.PENDENTE.getDescricao();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getStatusMensalidade() {
		return statusMensalidade;
	}

	public void setStatusMensalidade(String statusMensalidade) {
		this.statusMensalidade = statusMensalidade;
	}
}
