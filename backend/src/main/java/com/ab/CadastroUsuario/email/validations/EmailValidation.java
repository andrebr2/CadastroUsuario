package com.ab.CadastroUsuario.email.validations;

import org.springframework.stereotype.Component;

import com.ab.CadastroUsuario.email.dto.EmailDTO;

@Component
public class EmailValidation {

    public void validarEmailDTO(EmailDTO dto) {
        if (dto.getEnderecoEmail() == null || dto.getEnderecoEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode estar vazio.");
        }

        // Regex para validar formato de e-mail
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!dto.getEnderecoEmail().matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }

        if (dto.getUsuario() == null || dto.getUsuario().getId() == null) {
            throw new IllegalArgumentException("O usuário associado ao e-mail não pode ser nulo.");
        }
    }
}

