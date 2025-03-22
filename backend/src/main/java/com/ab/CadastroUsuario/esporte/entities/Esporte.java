package com.ab.CadastroUsuario.esporte.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_esporte")
public class Esporte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "O nome do esporte não pode estar vazio.")
	@Size(min = 3, max = 50, message = "O nome do esporte deve ter entre 3 e 50 caracteres.")
	private String nome;
	
	@Column(length = 255)
    @Size(max = 255, message = "O caminho da imagem não pode ter mais de 255 caracteres.")
	private String imagem;

	public Esporte() {

	}

	public Esporte(Long id, String nome, String imagem) {
		this.id = id;
		this.nome = nome;
		this.imagem = imagem;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
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
		Esporte other = (Esporte) obj;
		return Objects.equals(id, other.id);
	}

}
