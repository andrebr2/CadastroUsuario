package com.ab.CadastroUsuario.endereco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.CadastroUsuario.endereco.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
