package com.ab.CadastroUsuario.telefone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ab.CadastroUsuario.telefone.entities.Telefone;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	@Modifying
	@Query("DELETE FROM Telefone t WHERE t.usuario = :usuario")
	void deleteByUsuario(@Param("usuario") Usuario usuario);
}
