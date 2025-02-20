package com.ab.CadastroUsuario.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.CadastroUsuario.email.entities.EmailUsuario;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

public interface EmailRepository extends JpaRepository<EmailUsuario, Long> {

	boolean existsByEnderecoEmailAndUsuario(String enderecoEmail, Usuario usuario);

}
