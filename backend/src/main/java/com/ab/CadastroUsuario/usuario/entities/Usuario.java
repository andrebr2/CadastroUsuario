package com.ab.CadastroUsuario.usuario.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.ab.CadastroUsuario.endereco.entities.Endereco;
import com.ab.CadastroUsuario.esporte.entities.Esporte;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
	@Column(nullable = false, length = 100)
	private String nome;

	@NotBlank(message = "O sobrenome é obrigatório")
	@Size(min = 2, max = 100, message = "O sobrenome deve ter entre 2 e 100 caracteres")
	@Column(nullable = false, length = 100)
	private String sobrenome;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	private String fotoPerfil;

	@Size(max = 20, message = "O número do endereço deve ter no máximo 20 caracteres")
	private String nroEndereco;

	@Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name = "tb_usuario_esporte", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "esporte_id"))
	@JsonIgnore
	private Set<Esporte> esportesPreferidos = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_usuario_seguidores", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "seguidor_id"))
	@JsonIgnore
	private Set<Usuario> seguidores = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_usuario_seguindo", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "seguindo_id"))
	@JsonIgnore
	private Set<Usuario> seguindo = new HashSet<>();

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

	public Set<Esporte> getEsportesPreferidos() {
		return esportesPreferidos;
	}

	public void setEsportesPreferidos(Set<Esporte> esportesPreferidos) {
		this.esportesPreferidos = esportesPreferidos;
	}

	public Set<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Set<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public Set<Usuario> getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Set<Usuario> seguindo) {
		this.seguindo = seguindo;
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
