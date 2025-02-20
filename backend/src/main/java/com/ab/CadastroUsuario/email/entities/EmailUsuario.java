package com.ab.CadastroUsuario.email.entities;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "tb_email")
public class EmailUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O endereço de e-mail é obrigatório") // Garante que não seja nulo ou vazio
    @Email(message = "O endereço de e-mail deve ser válido") // Valida o formato do e-mail
	@Column(nullable = false)
	private String enderecoEmail;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public EmailUsuario() {

	}

	public EmailUsuario(Long id, String enderecoEmail, Usuario usuario) {
		this.id = id;
		this.enderecoEmail = enderecoEmail;
		this.usuario = usuario;
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
		EmailUsuario other = (EmailUsuario) obj;
		return Objects.equals(id, other.id);
	}

}
