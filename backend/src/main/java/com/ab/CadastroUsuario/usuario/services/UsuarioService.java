package com.ab.CadastroUsuario.usuario.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.CadastroUsuario.email.repositories.EmailRepository;
import com.ab.CadastroUsuario.endereco.dto.EnderecoDTO;
import com.ab.CadastroUsuario.endereco.entities.Endereco;
import com.ab.CadastroUsuario.endereco.repositories.EnderecoRepository;
import com.ab.CadastroUsuario.esporte.dto.EsporteDTO;
import com.ab.CadastroUsuario.esporte.entities.Esporte;
import com.ab.CadastroUsuario.esporte.repositories.EsporteRepository;
import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;
import com.ab.CadastroUsuario.telefone.repositories.TelefoneRepository;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDetalhadoDTO;
import com.ab.CadastroUsuario.usuario.entities.Usuario;
import com.ab.CadastroUsuario.usuario.repositories.UsuarioRepository;
import com.ab.CadastroUsuario.usuario.validations.UsuarioValidation;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EsporteRepository esporteRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	UsuarioValidation usuarioValidation;

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		Page<Usuario> result = repository.findAll(pageable);
		Page<UsuarioDTO> page = result.map(x -> new UsuarioDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id, boolean detalhado) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para ID: " + id));

		if (detalhado) {
			return new UsuarioDetalhadoDTO(usuario);
		}
		return new UsuarioDTO(usuario);
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		usuarioValidation.validarUsuarioDTO(dto);
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}

	@Transactional
	public UsuarioDTO upDate(Long id, UsuarioDTO dto) {
		try {
			usuarioValidation.validarUsuarioDTO(dto);
			Usuario entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));

		try {
			emailRepository.deleteByUsuario(usuario);
			telefoneRepository.deleteByUsuario(usuario);
			repository.delete(usuario);
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

		if (dto.getEndereco() != null && dto.getEndereco().getId() != null) {
			Endereco endereco = enderecoRepository.findById(dto.getEndereco().getId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Endereço não encontrado para ID: " + dto.getEndereco().getId()));
			entity.setEndereco(endereco);
		} else {
			entity.setEndereco(null);
		}

	}

	@Transactional
	public void seguirUsuario(Long usuarioId, Long seguirId) {
		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

		Usuario seguir = repository.findById(seguirId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário a ser seguido não encontrado"));

		usuario.getSeguindo().add(seguir);
		seguir.getSeguidores().add(usuario);

		repository.save(usuario);
		repository.save(seguir);
	}

	@Transactional
	public void deixarDeSeguirUsuario(Long usuarioId, Long deixarSeguirId) {
		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

		Usuario deixarSeguir = repository.findById(deixarSeguirId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário a deixar de seguir não encontrado"));

		usuario.getSeguindo().remove(deixarSeguir);
		deixarSeguir.getSeguidores().remove(usuario);

		repository.save(usuario);
		repository.save(deixarSeguir);
	}

	@Transactional
	public UsuarioDTO adicionarSeguidor(Long usuarioId, Long seguidorId) {
		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + usuarioId));

		Usuario seguidor = repository.findById(seguidorId)
				.orElseThrow(() -> new ResourceNotFoundException("Seguidor não encontrado: " + seguidorId));

		usuario.getSeguidores().add(seguidor);
		repository.save(usuario);

		return new UsuarioDTO(usuario);
	}

	@Transactional
	public UsuarioDTO removerSeguidor(Long usuarioId, Long seguidorId) {
		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + usuarioId));

		Usuario seguidor = repository.findById(seguidorId)
				.orElseThrow(() -> new ResourceNotFoundException("Seguidor não encontrado: " + seguidorId));

		if (usuario.getSeguidores().contains(seguidor)) {
			usuario.getSeguidores().remove(seguidor);
			repository.save(usuario);
		} else {
			throw new IllegalArgumentException("O usuário " + seguidorId + " não está seguindo " + usuarioId);
		}

		return new UsuarioDTO(usuario);
	}

	@Transactional(readOnly = true)
	public Set<UsuarioDTO> getSeguidores(Long id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o ID: " + id));

		usuario.getSeguidores();

		return usuario.getSeguidores().stream().map(UsuarioDTO::new).collect(Collectors.toSet());
	}

	@Transactional
	public UsuarioDTO adicionarEsporteFavorito(Long usuarioId, Long esporteId) {
		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para ID: " + usuarioId));

		Esporte esporte = esporteRepository.findById(esporteId)
				.orElseThrow(() -> new ResourceNotFoundException("Esporte não encontrado para ID: " + esporteId));

		usuario.getEsportesPreferidos().add(esporte);
		repository.save(usuario);

		return new UsuarioDetalhadoDTO(usuario);
	}

	@Transactional
	public UsuarioDTO removerEsporteFavorito(Long usuarioId, Long esporteId) {
		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para ID: " + usuarioId));

		Esporte esporte = esporteRepository.findById(esporteId)
				.orElseThrow(() -> new ResourceNotFoundException("Esporte não encontrado para ID: " + esporteId));

		usuario.getEsportesPreferidos().remove(esporte);
		repository.save(usuario);

		return new UsuarioDetalhadoDTO(usuario);
	}

	@Transactional(readOnly = true)
	public Set<EsporteDTO> getEsportesFavoritos(Long id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o ID: " + id));

		usuario.getEsportesPreferidos();

		return usuario.getEsportesPreferidos().stream().map(EsporteDTO::new).collect(Collectors.toSet());
	}

	public EnderecoDTO buscarEnderecoPorUsuarioId(Long usuarioId) {
		Endereco endereco = repository.findEnderecoByUsuarioId(usuarioId).orElseThrow(
				() -> new ResourceNotFoundException("Endereço não encontrado para o usuário ID: " + usuarioId));

		return new EnderecoDTO(endereco);
	}

}
