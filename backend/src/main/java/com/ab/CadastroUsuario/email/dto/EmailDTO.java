package com.ab.CadastroUsuario.email.dto;

import com.ab.CadastroUsuario.email.entities.Email;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;

public class EmailDTO {

	private Long id;
	private String enderecoEmail;
	private UsuarioDTO usuario;

	public EmailDTO() {

	}

	public EmailDTO(Long id, String enderecoEmail, UsuarioDTO usuario) {
		this.id = id;
		this.enderecoEmail = enderecoEmail;
		this.usuario = usuario;
	}

	public EmailDTO(Email entity) {
		id = entity.getId();
		enderecoEmail = entity.getEnderecoEmail();
		usuario = new UsuarioDTO(entity.getUsuario());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnderecoEmail() {
		return enderecoEmail;
	}

	public void setEnderecoEmail(String enderecoEmail) {
		this.enderecoEmail = enderecoEmail;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
