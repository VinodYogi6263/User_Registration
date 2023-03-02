package com.ss.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ss.response.General_Response;

@RestControllerAdvice
public class Application_Exception_Handler

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
	
	@ExceptionHandler(Internal_Exception.class)
	public ResponseEntity<General_Response> Internal_Exception(Internal_Exception e) {
		
		return new ResponseEntity<General_Response>(new General_Response(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(Null_Value_Exception.class)
	public ResponseEntity<General_Response> Null_Value_Exception(Null_Value_Exception e) {
		
		return new ResponseEntity<General_Response>(new General_Response(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	

	@ExceptionHandler(Empty_Result_Data_Access_Exception.class)
	public ResponseEntity<General_Response> Empty_Result_Data_Access_Exception(Empty_Result_Data_Access_Exception e) {
		
		return new ResponseEntity<General_Response>(new General_Response(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(No_Such_Element_Exception.class)
	public ResponseEntity<General_Response> No_Such_Element_Exception(No_Such_Element_Exception e) {
		
		return new ResponseEntity<General_Response>(new General_Response(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(Bad_Credentials_Exception_Handle.class)
	public ResponseEntity<General_Response> Bad_Credentials_Exception_Handle(Bad_Credentials_Exception_Handle e) {
		
		return new ResponseEntity<General_Response>(new General_Response(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Property_InvalidDataAccessApiUsageException.class)
	public ResponseEntity<General_Response> Property_InvalidDataAccessApiUsageException(Property_InvalidDataAccessApiUsageException e) {
		
		return new ResponseEntity<General_Response>(new General_Response(e.getMessage()+"  "+current),HttpStatus.BAD_REQUEST);

	}

}
