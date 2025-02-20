package com.ab.CadastroUsuario.telefone.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ab.CadastroUsuario.email.dto.EmailDTO;
import com.ab.CadastroUsuario.telefone.dto.DddDTO;
import com.ab.CadastroUsuario.telefone.services.DddService;

@RestController
@RequestMapping(value = "/ddds")
public class DddController {

	@Autowired
	private DddService service;

	@GetMapping
	public Page<DddDTO> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(value = "/{codigoArea}")
	public DddDTO findByCode(@PathVariable Integer codigoArea) {
		return service.findByCode(codigoArea);
	}

	@PostMapping
	public ResponseEntity<DddDTO> insert(@RequestBody DddDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoArea}")
				.buildAndExpand(dto.getCodigoArea()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping(value = "/{codigoArea}")
	public ResponseEntity<EmailDTO> delete(@PathVariable Integer codigoArea) {
		service.delete(codigoArea);
		return ResponseEntity.noContent().build();
	}
}
