package com.ab.CadastroUsuario.usuario.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ab.CadastroUsuario.endereco.entities.Endereco;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u.endereco FROM Usuario u WHERE u.id = :usuarioId")
	Optional<Endereco> findEnderecoByUsuarioId(@Param("usuarioId") Long usuarioId);

}
