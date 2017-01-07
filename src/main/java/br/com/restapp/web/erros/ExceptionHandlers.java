package br.com.restapp.web.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleFieldValidation(MethodArgumentNotValidException e) {
		return new ErrorResponse("O campo '" + e.getBindingResult().getFieldError().getField() + "' " 
	+ e.getBindingResult().getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse handlerInvalidSession(ExpiredJwtException e){
		return new ErrorResponse("Sessão inválida");
	}
}
