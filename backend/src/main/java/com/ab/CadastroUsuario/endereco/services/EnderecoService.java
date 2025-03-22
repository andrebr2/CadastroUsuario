package com.ab.CadastroUsuario.endereco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.CadastroUsuario.endereco.dto.EnderecoDTO;
import com.ab.CadastroUsuario.endereco.entities.Endereco;
import com.ab.CadastroUsuario.endereco.repositories.EnderecoRepository;
import com.ab.CadastroUsuario.exceptions.DataBaseException;
import com.ab.CadastroUsuario.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findAll(Pageable pageable) {
		Page<Endereco> result = repository.findAll(pageable);
		Page<EnderecoDTO> page = result.map(x -> new EnderecoDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public EnderecoDTO findById(Long id) {
		Endereco entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado! ID: " + id));
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {

		Endereco entity = new Endereco();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO upDate(Long id, EnderecoDTO dto) {
		try {
			Endereco entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new EnderecoDTO(entity);
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

	private void copyDtoToEntity(EnderecoDTO dto, Endereco entity) {
		entity.setCep(dto.getCep());
	}
}
