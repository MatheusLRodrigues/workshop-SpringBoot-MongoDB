package com.matheuslima.workshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matheuslima.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNot,
			HttpServletRequest requestServ) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError errorStan = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
				objectNot.getMessage(), requestServ.getRequestURI());

		return ResponseEntity.status(status).body(errorStan);

	}

}
