package com.ab.CadastroUsuario.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.CadastroUsuario.email.dto.EmailDTO;
import com.ab.CadastroUsuario.email.entities.EmailUsuario;
import com.ab.CadastroUsuario.email.repositories.EmailRepository;
import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;
import com.ab.CadastroUsuario.usuario.entities.Usuario;
import com.ab.CadastroUsuario.usuario.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmailService {

	@Autowired
	private EmailRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	public Page<EmailDTO> findAll(Pageable pageable) {
		Page<EmailUsuario> result = repository.findAll(pageable);
		Page<EmailDTO> page = result.map(x -> new EmailDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public EmailDTO findById(Long id) {
		EmailUsuario entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Email não encontrado! ID: " + id));
		return new EmailDTO(entity);
	}

	@Transactional
	public EmailDTO insert(EmailDTO dto) {

		// Validação: O e-mail não pode ser nulo ou vazio
		if (dto.getEnderecoEmail() == null || dto.getEnderecoEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("O endereço de e-mail não pode ser vazio.");
		}

		// Validação: Verifica se o usuário informado existe
		Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado para ID: " + dto.getUsuario().getId()));

		// Verifica se o e-mail já existe para o usuário (se necessário)
		if (repository.existsByEnderecoEmailAndUsuario(dto.getEnderecoEmail(), usuario)) {
			throw new DataIntegrityViolationException("Este e-mail já está cadastrado para o usuário informado.");
		}

		// Criando entidade e copiando os dados
		EmailUsuario entity = new EmailUsuario();
		copyDtoToEntity(dto, entity);

		// Garantindo que o usuário correto está sendo atribuído
		entity.setUsuario(usuario);

		// Salva no banco e retorna o DTO
		entity = repository.save(entity);
		return new EmailDTO(entity);
	}

	@Transactional
	public EmailDTO upDate(Long id, EmailDTO dto) {
		try {
			EmailUsuario entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new EmailDTO(entity);
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

	private void copyDtoToEntity(EmailDTO dto, EmailUsuario entity) {
		entity.setEnderecoEmail(dto.getEnderecoEmail());

	    // Verifica se o usuário não é nulo antes de criar a entidade
	    if (dto.getUsuario() != null && dto.getUsuario().getId() != null) {
	        Usuario usuario = new Usuario();
	        usuario.setId(dto.getUsuario().getId());
	        entity.setUsuario(usuario);
	    } else {
	        throw new IllegalArgumentException("Usuário é obrigatório e deve ter um ID válido.");
	    }
	}
}
