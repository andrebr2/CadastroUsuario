package com.ab.CadastroUsuario.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.CadastroUsuario.usuario.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
