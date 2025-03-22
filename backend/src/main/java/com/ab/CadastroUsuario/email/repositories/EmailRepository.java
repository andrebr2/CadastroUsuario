package com.ab.CadastroUsuario.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ab.CadastroUsuario.email.entities.EmailUsuario;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

public interface EmailRepository extends JpaRepository<EmailUsuario, Long> {

	boolean existsByEnderecoEmailAndUsuario(String enderecoEmail, Usuario usuario);
	
	@Modifying
	@Query("DELETE FROM EmailUsuario e WHERE e.usuario = :usuario")
	void deleteByUsuario(@Param("usuario") Usuario usuario);

}
