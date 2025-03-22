package com.ab.CadastroUsuario.esporte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.CadastroUsuario.esporte.entities.Esporte;

public interface EsporteRepository extends JpaRepository<Esporte, Long> {

}