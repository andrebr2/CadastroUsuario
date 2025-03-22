package com.ab.CadastroUsuario.esporte.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.CadastroUsuario.esporte.dto.EsporteDTO;
import com.ab.CadastroUsuario.esporte.entities.Esporte;
import com.ab.CadastroUsuario.esporte.repositories.EsporteRepository;
import com.ab.CadastroUsuario.esporte.validations.EsporteValidation;
import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EsporteService {

	@Autowired
	private EsporteRepository repository;
	
	@Autowired
    private EsporteValidation esporteValidation;

	@Transactional(readOnly = true)
	public Page<EsporteDTO> findAll(Pageable pageable) {

		Page<Esporte> result = repository.findAll(pageable);
		Page<EsporteDTO> page = result.map(x -> new EsporteDTO(x));
		return page;

	}

	@Transactional(readOnly = true)
	public EsporteDTO findById(Long id) {
	    Esporte result = repository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Esporte não encontrado para ID: " + id));
	    return new EsporteDTO(result);
	}


	@Transactional
	public EsporteDTO insert(EsporteDTO dto) {
		esporteValidation.validarEsporteDTO(dto);
		Esporte entity = new Esporte();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EsporteDTO(entity);
	}

	@Transactional
	public EsporteDTO upDate(Long id, EsporteDTO dto) {

		try {
			esporteValidation.validarEsporteDTO(dto);
			Esporte entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			return new EsporteDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade.");
		}
	}

	private void copyDtoToEntity(EsporteDTO dto, Esporte entity) {
		entity.setNome(dto.getNome());
		entity.setImagem(dto.getImagem());
	}

}
