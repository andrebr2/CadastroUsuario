package com.ab.CadastroUsuario.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// TRATAMENTO DE ID NÃO ENCONTRADO
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), "Recurso não encontrado", e.getMessage(),
				request.getDescription(false));
		return ResponseEntity.status(status).body(err);
	}

	// TRATAMENTO DE VIOLAÇÃO DE INTEGRIDADE (CONFLITO)
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> handleDatabaseException(DataBaseException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), "Erro de banco de dados", e.getMessage(),
				request.getDescription(false));
		return ResponseEntity.status(status).body(err);
	}
}
