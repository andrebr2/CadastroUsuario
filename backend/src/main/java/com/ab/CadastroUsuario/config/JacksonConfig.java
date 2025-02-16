package com.ab.CadastroUsuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		// Habilita saída formatada (opcional)
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		// Suporte para Java 8 Time API (caso esteja usando LocalDate, LocalDateTime,
		// etc.)
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper;
	}
}