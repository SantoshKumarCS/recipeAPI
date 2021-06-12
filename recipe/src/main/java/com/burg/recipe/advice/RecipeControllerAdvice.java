package com.burg.recipe.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.burg.recipe.custom.exception.EmptyListException;

//@ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions across the whole application in one global handling component
@ControllerAdvice
public class RecipeControllerAdvice extends ResponseEntityExceptionHandler{
	
	//global exception handling code for invalid method type requested
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Please change your http method type", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyListException emptyInputException){
		return new ResponseEntity<String>("Recipe list is empty, Please look into it", HttpStatus.BAD_REQUEST);
	}
	
	//global exception to inform user if the id given by the client user is not present in the db  
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No value present in db for the given recipeId", HttpStatus.NOT_FOUND);
	}
	
	
}
