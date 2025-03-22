package com.ab.CadastroUsuario.telefone.validations;

import org.springframework.stereotype.Component;

import com.ab.CadastroUsuario.telefone.dto.TelefoneDTO;

@Component
public class TelefoneValidation {
	public void validarTelefoneDTO(TelefoneDTO dto) {
		 if (dto.getNroTelefone() == null || dto.getNroTelefone().trim().isEmpty()) {
	            throw new IllegalArgumentException("O número do telefone não pode estar vazio.");
	        }

	        if (!dto.getNroTelefone().matches("^[1-9]\\d{7}$|^9[1-9]\\d{7}$")) {
	            throw new IllegalArgumentException("O número do telefone deve ter 8 dígitos para fixo ou 9 dígitos para celular, sem espaços ou caracteres especiais.");
	        }

	        if (dto.getDdd() == null || dto.getDdd().getCodigoArea() == null) {
	            throw new IllegalArgumentException("O código DDD é obrigatório.");
	        }

	        if (dto.getDdd().getCodigoArea() < 11 || dto.getDdd().getCodigoArea() > 99) {
	            throw new IllegalArgumentException("O código DDD deve estar entre 11 e 99.");
	        }
	}
}
