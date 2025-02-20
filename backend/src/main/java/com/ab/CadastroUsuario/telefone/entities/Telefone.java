package com.ab.CadastroUsuario.telefone.entities;

import java.io.Serializable;
import java.util.Objects;

import com.ab.CadastroUsuario.usuario.entities.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O número de telefone é obrigatório") // Garante que não seja nulo ou vazio
	@Pattern(regexp = "^\\d{4,5}-\\d{4}$", message = "O número de telefone deve estar no formato XXXXX-XXXX ou XXXX-XXXX")
    @Column(nullable = false)
	private String nroTelefone;

	@ManyToOne
	@JoinColumn(name = "codigo_area", nullable = false)
	private Ddd codigoArea;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Telefone() {

	}

	public Telefone(Long id, String nroTelefone, Ddd codigoArea, Usuario usuario) {
		this.id = id;
		this.nroTelefone = nroTelefone;
		this.codigoArea = codigoArea;
		this.usuario = usuario;
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
