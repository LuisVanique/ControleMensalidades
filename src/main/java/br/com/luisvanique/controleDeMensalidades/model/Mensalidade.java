package br.com.luisvanique.controleDeMensalidades.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
	@JoinColumn(name = "aluno_id")
    private Aluno aluno;
	
	
	@Column(name = "DATA_VENCIMENTO")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@Column(name= "VALOR")
	private Double valor;
	
	@Column(name = "STATUS")
	private Integer status;

	

	public Mensalidade(){
		
	}
	
	public Long getId() {
		return id;
	}
	
	public Mensalidade(Aluno aluno, LocalDate dataVencimento, Double valor, Integer status) {
		super();
		this.aluno = aluno;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.status = status;
	}



	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getStatusMensalidade() {
		return status;
	}

	public void setStatusMensalidade(Integer statusMensalidade) {
		this.status = statusMensalidade;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
