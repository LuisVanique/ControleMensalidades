package br.com.luisvanique.controleDeMensalidades.model;

public enum StatusMensalidade {
	PENDENTE(1, "Pendente"), PAGA(2, "Paga"), VENCIDA(3, "Vencida");

	private Integer status;
	private String descricao;

	StatusMensalidade(Integer i, String descricao) {
		this.setStatus(i);
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
