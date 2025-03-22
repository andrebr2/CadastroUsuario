package com.ab.CadastroUsuario.esporte.dto;

import com.ab.CadastroUsuario.esporte.entities.Esporte;

public class EsporteDTO {

	private Long id;
	private String nome;
	private String imagem;

	public EsporteDTO() {

	}

	public EsporteDTO(Long id, String nome, String imagem) {
		this.id = id;
		this.nome = nome;
		this.imagem = imagem;
	}

	public EsporteDTO(Esporte entity) {
		id = entity.getId();
		nome = entity.getNome();
		imagem = entity.getImagem();
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

}
