package com.ab.CadastroUsuario.telefone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;
import com.ab.CadastroUsuario.telefone.dto.TelefoneDTO;
import com.ab.CadastroUsuario.telefone.entities.Ddd;
import com.ab.CadastroUsuario.telefone.entities.Telefone;
import com.ab.CadastroUsuario.telefone.repositories.TelefoneRepository;
import com.ab.CadastroUsuario.usuario.dto.UsuarioDTO;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository repository;

	@Transactional(readOnly = true)
	public Page<TelefoneDTO> findAll(Pageable pageable) {
		Page<Telefone> result = repository.findAll(pageable);
		Page<TelefoneDTO> page = result.map(x -> new TelefoneDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public TelefoneDTO findById(Long id) {
		Telefone entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado! ID: " + id));
		return new TelefoneDTO(entity);
	}

	@Transactional
	public TelefoneDTO insert(TelefoneDTO dto) {

		// Validação: número de telefone não pode ser nulo ou vazio
	    if (dto.getNroTelefone() == null || dto.getNroTelefone().trim().isEmpty()) {
	        throw new IllegalArgumentException("O número do telefone não pode estar vazio.");
	    }

	    // Validação: DDD não pode ser nulo
	    if (dto.getDdd() == null || dto.getDdd().getCodigoArea() == null) {
	        throw new IllegalArgumentException("O código de área (DDD) não pode ser nulo.");
	    }

	    // Criar a entidade Telefone
	    Telefone entity = new Telefone();
	    copyDtoToEntity(dto, entity); // Método que copia os dados do DTO para a entidade

	    // Salvar a entidade no banco
	    entity = repository.save(entity);
	    return new TelefoneDTO(entity);
	
	}

	@Transactional
	public TelefoneDTO upDate(Long id, TelefoneDTO dto) {
		try {
			Telefone entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TelefoneDTO(entity);
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

	private void copyDtoToEntity(TelefoneDTO dto, Telefone entity) {
		entity.setNroTelefone(dto.getNroTelefone());

		Ddd ddd = new Ddd();
		ddd.setCodigoArea(dto.getDdd().getCodigoArea());
		entity.setCodigoArea(ddd);

		Usuario usuario = new Usuario();
		usuario.setId(dto.getUsuario().getId());
		entity.setUsuario(usuario);
	}
}
