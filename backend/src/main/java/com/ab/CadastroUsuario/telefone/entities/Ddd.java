package com.ab.CadastroUsuario.telefone.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ddd")
public class Ddd {

	@Id
	@Column(name = "codigoArea", nullable = false)
	private Integer codigoArea;

	public Ddd() {

	}

	public Ddd(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoArea);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ddd other = (Ddd) obj;
		return Objects.equals(codigoArea, other.codigoArea);
	}

}
