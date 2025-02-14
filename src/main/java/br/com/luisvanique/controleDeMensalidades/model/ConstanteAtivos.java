package br.com.luisvanique.controleDeMensalidades.model;

public enum ConstanteAtivos {
	
	INATIVO("N"), ATIVO("S");
	
	private String valor;
	
	ConstanteAtivos(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
}
