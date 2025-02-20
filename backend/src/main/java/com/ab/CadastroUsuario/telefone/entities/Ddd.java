package com.ab.CadastroUsuario.telefone.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_ddd")
public class Ddd {

	@Id
	@Column(name = "codigoArea", nullable = false)
	@NotNull(message = "O código de área é obrigatório") // Garante que não seja nulo
    @Min(value = 10, message = "O código de área deve ter pelo menos 2 dígitos") // Garante que seja um número válido
    @Max(value = 99, message = "O código de área deve ter no máximo 2 dígitos") // Limita a 2 dígitos
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
