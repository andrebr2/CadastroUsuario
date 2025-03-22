package com.ab.CadastroUsuario.telefone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.ab.CadastroUsuario.telefone.validations.TelefoneValidation;
import com.ab.CadastroUsuario.usuario.entities.Usuario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository repository;
	
	@Autowired
	TelefoneValidation telefoneValidation;

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
		telefoneValidation.validarTelefoneDTO(dto);
	    Telefone entity = new Telefone();
	    copyDtoToEntity(dto, entity);
	    entity = repository.save(entity);
	    return new TelefoneDTO(entity);
	
	}

	@Transactional
	public TelefoneDTO upDate(Long id, TelefoneDTO dto) {
		try {
			telefoneValidation.validarTelefoneDTO(dto);
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
