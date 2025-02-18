package com.ab.CadastroUsuario.endereco.dto;

import com.ab.CadastroUsuario.endereco.entities.Endereco;

public class EnderecoDTO {

	private Long id;
	private String cep;

	public EnderecoDTO() {

	}

	public EnderecoDTO(Long id, String cep) {
		this.id = id;
		this.cep = cep;
	}

	public EnderecoDTO(Endereco entity) {
		id = entity.getId();
		cep = entity.getCep();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
