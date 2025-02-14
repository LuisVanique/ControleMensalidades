package br.com.luisvanique.controleDeMensalidades.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_parametros")
public class Parametro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "descricaoParametro")
	private String descricaoParametro;
	
	@Column(name = "valorParametro")
	private String valorParametro;

	@Column(name = "ativo")
	private String ativo = "S";

	public Parametro(){
		
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoParametro, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		return Objects.equals(descricaoParametro, other.descricaoParametro) && Objects.equals(id, other.id);
	}		
}
