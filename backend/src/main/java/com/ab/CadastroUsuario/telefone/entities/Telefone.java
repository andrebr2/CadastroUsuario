package com.ab.CadastroUsuario.telefone.entities;

import java.io.Serializable;
import java.util.Objects;

import com.ab.CadastroUsuario.usuario.entities.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroTelefone;

	@ManyToOne
	@JoinColumn(name = "codigo_area", referencedColumnName = "codigoArea")
	private Ddd codigoArea;

	@ManyToOne
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	private Usuario usuario;

	public Telefone() {

	}

	public Telefone(Long id, String numeroTelefone, Ddd codigoArea, Usuario usuario) {
		this.id = id;
		this.numeroTelefone = numeroTelefone;
		this.codigoArea = codigoArea;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public Ddd getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Ddd codigoArea) {
		this.codigoArea = codigoArea;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id);
	}

}
