package com.ab.CadastroUsuario.telefone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.CadastroUsuario.telefone.entities.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
