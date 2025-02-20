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
import com.ab.CadastroUsuario.telefone.dto.DddDTO;
import com.ab.CadastroUsuario.telefone.dto.TelefoneDTO;
import com.ab.CadastroUsuario.telefone.entities.Ddd;
import com.ab.CadastroUsuario.telefone.entities.Telefone;
import com.ab.CadastroUsuario.telefone.repositories.DddRepository;

@Service
public class DddService {

	@Autowired
	private DddRepository repository;

	@Transactional(readOnly = true)
	public Page<DddDTO> findAll(Pageable pageable) {
		Page<Ddd> result = repository.findAll(pageable);
		Page<DddDTO> page = result.map(x -> new DddDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public DddDTO findByCode(Integer codigoArea) {
		Ddd entity = repository.findById(codigoArea)
				.orElseThrow(() -> new ResourceNotFoundException("DDD não encontrado! Código Área: " + codigoArea));
		return new DddDTO(entity);
	}

	@Transactional
	public DddDTO insert(DddDTO dto) {
		if (repository.existsById(dto.getCodigoArea())) {
			throw new IllegalArgumentException("Código de área já existe!");
		}
		Ddd entity = new Ddd();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new DddDTO(entity);
	}

	public void delete(Integer codigoArea) {
		if (!repository.existsById(codigoArea)) {
	        throw new ResourceNotFoundException("Código de Área não encontrado: " + codigoArea);
	    }

	    try {
	        repository.deleteById(codigoArea);
	    } catch (DataIntegrityViolationException e) {
	        throw new DataBaseException("Violação de integridade");
	    }
	}

	private void copyDtoToEntity(DddDTO dto, Ddd entity) {
		entity.setCodigoArea(dto.getCodigoArea());
	}
}
