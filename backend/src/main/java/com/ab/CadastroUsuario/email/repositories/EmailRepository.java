package com.ab.CadastroUsuario.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.CadastroUsuario.email.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
