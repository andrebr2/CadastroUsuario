package com.ab.CadastroUsuario.usuario.entities;

import java.io.Serializable;
import java.util.Objects;

import com.ab.CadastroUsuario.endereco.entities.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;

	@Enumerated(EnumType.STRING) // Salva como texto no banco de dados
	private Genero genero;

	private String fotoPerfil;
	private String nroEndereco;
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Usuario() {

	}

	public Usuario(Long id, String nome, String sobrenome, Genero genero, String fotoPerfil, String nroEndereco,
			String complemento, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.fotoPerfil = fotoPerfil;
		this.nroEndereco = nroEndereco;
		this.complemento = complemento;
		this.endereco = endereco;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
