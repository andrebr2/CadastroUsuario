package com.ab.CadastroUsuario.usuario.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.ab.CadastroUsuario.esporte.dto.EsporteDTO;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

public class UsuarioDetalhadoDTO extends UsuarioDTO {

	private Set<EsporteDTO> esportesPreferidos = new HashSet<>();
	private Set<Long> seguidoresIds = new HashSet<>();
	private Set<Long> seguindoIds = new HashSet<>();
	
	public UsuarioDetalhadoDTO(Usuario entity) {
        super(entity);
        this.esportesPreferidos = entity.getEsportesPreferidos().stream()
                .map(EsporteDTO::new)
                .collect(Collectors.toSet());

        this.seguidoresIds = entity.getSeguidores().stream()
                .map(Usuario::getId)
                .collect(Collectors.toSet());

        this.seguindoIds = entity.getSeguindo().stream()
                .map(Usuario::getId)
                .collect(Collectors.toSet());
    }

	public Set<EsporteDTO> getEsportesPreferidos() {
		return esportesPreferidos;
	}

	public void setEsportesPreferidos(Set<EsporteDTO> esportesPreferidos) {
		this.esportesPreferidos = esportesPreferidos;
	}

	public Set<Long> getSeguidoresIds() {
		return seguidoresIds;
	}

	public void setSeguidoresIds(Set<Long> seguidoresIds) {
		this.seguidoresIds = seguidoresIds;
	}

	public Set<Long> getSeguindoIds() {
		return seguindoIds;
	}

	public void setSeguindoIds(Set<Long> seguindoIds) {
		this.seguindoIds = seguindoIds;
	}
	
	
	
}
