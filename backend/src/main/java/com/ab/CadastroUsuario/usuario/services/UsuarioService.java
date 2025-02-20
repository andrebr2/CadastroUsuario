package com.ab.CadastroUsuario.usuario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.CadastroUsuario.endereco.entities.Endereco;
import com.ab.CadastroUsuario.endereco.repositories.EnderecoRepository;
import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;
import com.ab.CadastroUsuario.usuario.entities.Usuario;
import com.ab.CadastroUsuario.usuario.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		Page<Usuario> result = repository.findAll(pageable);
		Page<UsuarioDTO> page = result.map(x -> new UsuarioDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {

		Usuario entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado! ID: " + id));
		return new UsuarioDTO(entity);
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {

		// Validação: Nome e sobrenome não podem ser nulos ou vazios
		if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do usuário não pode estar vazio.");
		}
		if (dto.getSobrenome() == null || dto.getSobrenome().trim().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome do usuário não pode estar vazio.");
		}

		// Criando a entidade e copiando os dados do DTO
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);

		// Salvando a entidade no banco
		entity = repository.save(entity);

		return new UsuarioDTO(entity);
	}

	@Transactional
	public UsuarioDTO upDate(Long id, UsuarioDTO dto) {
		try {
			Usuario entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNome(dto.getNome().trim());
		entity.setSobrenome(dto.getSobrenome().trim());
		entity.setGenero(dto.getGenero());
		entity.setFotoPerfil(dto.getFotoPerfil());
		entity.setNroEndereco(dto.getNroEndereco());
		entity.setComplemento(dto.getComplemento());

		// Garantindo que o endereço seja criado corretamente
		if (dto.getEndereco() != null && dto.getEndereco().getId() != null) {
			Endereco endereco = enderecoRepository.findById(dto.getEndereco().getId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Endereço não encontrado para ID: " + dto.getEndereco().getId()));
			entity.setEndereco(endereco);
		} else {
			entity.setEndereco(null); // Se não houver endereço válido, define como null
		}

	}
}
