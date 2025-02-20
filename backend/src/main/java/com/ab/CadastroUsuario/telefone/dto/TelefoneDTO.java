package com.ab.CadastroUsuario.telefone.dto;

import com.ab.CadastroUsuario.telefone.entities.Telefone;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;

public class TelefoneDTO {

	private Long id;
	private String nroTelefone;
	private DddDTO ddd;
	private UsuarioDTO usuario;

	public TelefoneDTO() {

	}

	public TelefoneDTO(Long id, String nroTelefone, DddDTO ddd, UsuarioDTO usuario) {
		this.id = id;
		this.nroTelefone = nroTelefone;
		this.ddd = ddd;
		this.usuario = usuario;
	}

	public TelefoneDTO(Telefone entity) {
		id = entity.getId();
		nroTelefone = entity.getNroTelefone();
		ddd = new DddDTO(entity.getCodigoArea());
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

	public DddDTO getDdd() {
		return ddd;
	}

	public void setDdd(DddDTO ddd) {
		this.ddd = ddd;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
