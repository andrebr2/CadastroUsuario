package com.ab.CadastroUsuario.esporte.validations;

import org.springframework.stereotype.Component;

import com.ab.CadastroUsuario.esporte.dto.EsporteDTO;

@Component
public class EsporteValidation {

	public void validarEsporteDTO(EsporteDTO dto) {
		if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do esporte não pode estar vazio.");
		}
		if (dto.getNome().length() < 3 || dto.getNome().length() > 50) {
			throw new IllegalArgumentException("O nome do esporte deve ter entre 3 e 50 caracteres.");
		}
		if (dto.getImagem() != null && dto.getImagem().length() > 255) {
			throw new IllegalArgumentException("O caminho da imagem não pode ter mais de 255 caracteres.");
		}
	}
}