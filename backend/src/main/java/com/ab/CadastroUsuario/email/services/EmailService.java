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
import com.ab.CadastroUsuario.email.validations.EmailValidation;
import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmailService {

	@Autowired
	private EmailRepository repository;

	@Autowired
	EmailValidation emailValidation;

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
		emailValidation.validarEmailDTO(dto);
		EmailUsuario entity = new EmailUsuario();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EmailDTO(entity);
	}

	@Transactional
	public EmailDTO upDate(Long id, EmailDTO dto) {
		try {
			emailValidation.validarEmailDTO(dto);
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

		if (dto.getUsuario() != null && dto.getUsuario().getId() != null) {
			Usuario usuario = new Usuario();
			usuario.setId(dto.getUsuario().getId());
			entity.setUsuario(usuario);
		} else {
			throw new IllegalArgumentException("Usuário é obrigatório e deve ter um ID válido.");
		}
	}
}
