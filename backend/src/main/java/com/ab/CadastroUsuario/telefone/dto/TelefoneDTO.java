package com.ab.CadastroUsuario.telefone.dto;

import com.ab.CadastroUsuario.telefone.entities.Telefone;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;

public class TelefoneDTO {

	private Long id;
	private String nroTelefone;
	private DddDTO codigoArea;
	private UsuarioDTO usuario;

	public TelefoneDTO() {

	}

	public TelefoneDTO(Long id, String nroTelefone, DddDTO codigoArea, UsuarioDTO usuario) {
		this.id = id;
		this.nroTelefone = nroTelefone;
		this.codigoArea = codigoArea;
		this.usuario = usuario;
	}

	public TelefoneDTO(Telefone entity) {
		id = entity.getId();
		nroTelefone = entity.getNroTelefone();
		codigoArea = new DddDTO(entity.getCodigoArea());
		usuario = new UsuarioDTO(entity.getUsuario());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNroTelefone() {
		return nroTelefone;
	}

	public void setNroTelefone(String nroTelefone) {
		this.nroTelefone = nroTelefone;
	}

	public DddDTO getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(DddDTO codigoArea) {
		this.codigoArea = codigoArea;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
