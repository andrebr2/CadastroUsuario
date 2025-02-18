package com.ab.CadastroUsuario.telefone.dto;

import com.ab.CadastroUsuario.telefone.entities.Ddd;

public class DddDTO {

	private Integer codigoArea;

	public DddDTO() {

	}

	public DddDTO(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public DddDTO(Ddd entity) {
		codigoArea = entity.getCodigoArea();
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

}
