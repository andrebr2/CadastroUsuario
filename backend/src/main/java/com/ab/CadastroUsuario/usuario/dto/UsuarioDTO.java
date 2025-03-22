package com.ab.CadastroUsuario.usuario.dto;

import com.ab.CadastroUsuario.endereco.dto.EnderecoDTO;
import com.ab.CadastroUsuario.usuario.entities.Genero;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private Genero genero;
	private String fotoPerfil;
	private String nroEndereco;
	private String complemento;
	private EnderecoDTO endereco;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Long id, String nome, String sobrenome, Genero genero, String fotoPerfil, String nroEndereco,
			String complemento, EnderecoDTO endereco) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.fotoPerfil = fotoPerfil;
		this.nroEndereco = nroEndereco;
		this.complemento = complemento;
		this.endereco = endereco;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		sobrenome = entity.getSobrenome();
		genero = entity.getGenero();
		fotoPerfil = entity.getFotoPerfil();
		nroEndereco = entity.getNroEndereco();
		complemento = entity.getComplemento();
		endereco = new EnderecoDTO(entity.getEndereco());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getNroEndereco() {
		return nroEndereco;
	}

	public void setNroEndereco(String nroEndereco) {
		this.nroEndereco = nroEndereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

}
