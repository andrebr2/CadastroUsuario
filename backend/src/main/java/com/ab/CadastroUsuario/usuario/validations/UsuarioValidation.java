package com.ab.CadastroUsuario.usuario.validations;

import org.springframework.stereotype.Component;

import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;

@Component
public class UsuarioValidation {

	public void validarUsuarioDTO(UsuarioDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("O usuário não pode ser nulo.");
		}

		if (dto.getNome() == null || dto.getNome().trim().isEmpty() || dto.getNome().length() < 2
				|| dto.getNome().length() > 50) {
			throw new IllegalArgumentException("O nome deve ter entre 2 e 50 caracteres.");
		}

		if (dto.getSobrenome() == null || dto.getSobrenome().trim().isEmpty() || dto.getSobrenome().length() < 2
				|| dto.getSobrenome().length() > 50) {
			throw new IllegalArgumentException("O sobrenome deve ter entre 2 e 50 caracteres.");
		}

		if (dto.getGenero() == null) {
			throw new IllegalArgumentException("O gênero não pode ser nulo.");
		}

		if (dto.getFotoPerfil() != null && dto.getFotoPerfil().length() > 255) {
			throw new IllegalArgumentException("A URL da foto de perfil não pode ter mais de 255 caracteres.");
		}

		if (dto.getNroEndereco() != null && dto.getNroEndereco().length() > 10) {
			throw new IllegalArgumentException("O número do endereço não pode ter mais de 10 caracteres.");
		}

		if (dto.getComplemento() != null && dto.getComplemento().length() > 100) {
			throw new IllegalArgumentException("O complemento não pode ter mais de 100 caracteres.");
		}

	}
}
