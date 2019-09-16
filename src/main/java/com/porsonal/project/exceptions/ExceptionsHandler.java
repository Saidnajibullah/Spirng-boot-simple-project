package com.porsonal.project.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.porsonal.project.customException.CustomException;
import com.porsonal.project.errorModel.ErrorModel;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> genericExceptionHandler(Exception ex, WebRequest request) {
		String errorMessage = ex.getLocalizedMessage();
		if(errorMessage != null) errorMessage = ex.toString();
		ErrorModel errorModel = new ErrorModel(new Date(), errorMessage);
		return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> nullPointerExceptionHandler(NullPointerException ex, WebRequest request) {
		String errorMessage = ex.getLocalizedMessage();
		if(errorMessage != null) errorMessage = ex.toString();
		ErrorModel errorModel = new ErrorModel(new Date(), errorMessage);
		return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {CustomException.class})
	public ResponseEntity<Object> customExceptionHandler(CustomException ex, WebRequest request) {
		String errorMessage = ex.getLocalizedMessage();
		if(errorMessage != null) errorMessage = ex.toString();
		ErrorModel errorModel = new ErrorModel(new Date(), errorMessage);
		return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class, CustomException.class})
	public ResponseEntity<Object> combindedExceptionHandler(Exception ex, WebRequest request) {
		String errorMessage = ex.getLocalizedMessage();
		if(errorMessage != null) errorMessage = ex.toString();
		ErrorModel errorModel = new ErrorModel(new Date(), errorMessage);
		return new ResponseEntity<>(errorModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
