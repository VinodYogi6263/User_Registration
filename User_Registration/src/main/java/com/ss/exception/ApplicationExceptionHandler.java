package com.ss.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ss.response.GeneralResponse;

@RestControllerAdvice
public class ApplicationExceptionHandler

{
	LocalDateTime current = LocalDateTime.now();
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> HandleInvalidArgument(MethodArgumentNotValidException ex) 
	{
		Map<String, String> errormap = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error ->
		{
			errormap.put(error.getField(), error.getDefaultMessage());
		}
		);

		return errormap;

	}
	
	@ExceptionHandler(InternalException.class)
	public ResponseEntity<GeneralResponse> Internal_Exception(InternalException e) {
		
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(NullValueException.class)
	public ResponseEntity<GeneralResponse> Null_Value_Exception(NullValueException e) {
		
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.NOT_FOUND);

	}
	

	@ExceptionHandler(EmptyResultDataAccessExceptionHandle.class)
	public ResponseEntity<GeneralResponse> Empty_Result_Data_Access_Exception(EmptyResultDataAccessExceptionHandle e) {
		
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(NoSuchElementExceptionHandle.class)
	public ResponseEntity<GeneralResponse> No_Such_Element_Exception(NoSuchElementExceptionHandle e) {
		
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(BadCredentialsExceptionHandle.class)
	public ResponseEntity<GeneralResponse> Bad_Credentials_Exception_Handle(BadCredentialsExceptionHandle e) {
		
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidProperty.class)
	public ResponseEntity<GeneralResponse> Property_InvalidDataAccessApiUsageException(InvalidProperty e) {
		
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<GeneralResponse> handleAccessDeniedException(AccessDeniedException e) 
	{
		return new ResponseEntity<GeneralResponse>(new GeneralResponse(e.getMessage()+"  "+current),HttpStatus.FORBIDDEN);
	}

}
