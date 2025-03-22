package com.ab.CadastroUsuario.usuario.controllers;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ab.CadastroUsuario.endereco.dto.EnderecoDTO;
import com.ab.CadastroUsuario.esporte.dto.EsporteDTO;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;
import com.ab.CadastroUsuario.usuario.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id,
			@RequestParam(defaultValue = "false") boolean detalhado) {

		UsuarioDTO usuarioDTO = service.findById(id, detalhado);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> upDate(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		dto = service.upDate(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{usuarioId}/seguir/{seguirId}")
	public ResponseEntity<Void> seguirUsuario(@PathVariable Long usuarioId, @PathVariable Long seguirId) {
		service.seguirUsuario(usuarioId, seguirId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{usuarioId}/deixar-seguir/{deixarSeguirId}")
	public ResponseEntity<Void> deixarDeSeguir(@PathVariable Long usuarioId, @PathVariable Long deixarSeguirId) {
		service.deixarDeSeguirUsuario(usuarioId, deixarSeguirId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{usuarioId}/adicionar-seguidor/{seguidorId}")
	public ResponseEntity<UsuarioDTO> adicionarSeguidor(@PathVariable Long usuarioId, @PathVariable Long seguidorId) {

		UsuarioDTO usuarioDTO = service.adicionarSeguidor(usuarioId, seguidorId);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@DeleteMapping("/{usuarioId}/remover-seguidor/{seguidorId}")
	public ResponseEntity<UsuarioDTO> removerSeguidor(@PathVariable Long usuarioId, @PathVariable Long seguidorId) {

		UsuarioDTO usuarioDTO = service.removerSeguidor(usuarioId, seguidorId);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@GetMapping("/{id}/seguidores")
	public ResponseEntity<Set<UsuarioDTO>> getSeguidores(@PathVariable Long id) {
		Set<UsuarioDTO> seguidores = service.getSeguidores(id);
		return ResponseEntity.ok(seguidores);
	}

	@PostMapping("/{usuarioId}/esportes/{esporteId}")
	public ResponseEntity<UsuarioDTO> adicionarEsporteFavorito(@PathVariable Long usuarioId,
			@PathVariable Long esporteId) {

		UsuarioDTO usuarioDTO = service.adicionarEsporteFavorito(usuarioId, esporteId);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@DeleteMapping("/{usuarioId}/esportes/{esporteId}")
	public ResponseEntity<UsuarioDTO> removerEsporteFavorito(@PathVariable Long usuarioId,
			@PathVariable Long esporteId) {

		UsuarioDTO usuarioDTO = service.removerEsporteFavorito(usuarioId, esporteId);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@GetMapping("/{id}/esportes")
	public ResponseEntity<Set<EsporteDTO>> getEsportesFavoritos(@PathVariable Long id) {
		Set<EsporteDTO> esportes = service.getEsportesFavoritos(id);
		return ResponseEntity.ok(esportes);
	}

	@GetMapping("/{id}/endereco")
	public ResponseEntity<EnderecoDTO> buscarEndereco(@PathVariable Long id) {
		EnderecoDTO enderecoDTO = service.buscarEnderecoPorUsuarioId(id);
		return ResponseEntity.ok().body(enderecoDTO);
	}

}
