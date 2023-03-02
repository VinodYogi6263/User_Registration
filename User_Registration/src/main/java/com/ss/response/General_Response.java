package com.ss.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class General_Response
{

	private String message;
	
	private Object contains;

	public General_Response(String message) {
		super();
		this.message = message;
	}
	
	

}
