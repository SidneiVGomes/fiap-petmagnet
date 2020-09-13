package br.com.petmagnet.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppResourceExpectionHandler {
	
	@ExceptionHandler(AppBeanNotFoundException.class)
	public ResponseEntity<AppErrorMessageDefault> entityNotFound(AppBeanNotFoundException e, HttpServletRequest request){
		AppErrorMessageDefault errorMsg = new AppErrorMessageDefault();
		errorMsg.setTimestamp(Instant.now());
		errorMsg.setStatus(HttpStatus.NOT_FOUND.value());
		errorMsg.setError("Recurso Não Localizado");
		errorMsg.setMessage(e.getMessage());
		errorMsg.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
	}
}
