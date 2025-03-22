package com.ab.CadastroUsuario.telefone.validations;

import org.springframework.stereotype.Component;

import com.ab.CadastroUsuario.telefone.dto.DddDTO;

@Component
public class DddValidation {

	public void validarDddDTO(DddDTO dto) {
		if (dto.getCodigoArea() == null) {
			throw new IllegalArgumentException("O código DDD não pode ser nulo.");
		}

		if (dto.getCodigoArea() < 11 || dto.getCodigoArea() > 99) {
			throw new IllegalArgumentException("O código DDD deve estar entre 11 e 99.");
		}
	}
}
