package com.ss.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.lang.Nullable;

import com.ss.constant.Constants_Message;

import lombok.Data;

@Data
public class User_Request 
{
	@Email(message =Constants_Message.VALID_EMAIL)
	private String request_email;

	@NotBlank(message =Constants_Message.VALID_FIRST_NAME)
	private String request_first_name;

	@NotBlank(message=Constants_Message.VALID_LAST_NAME)
	private String request_last_name;

	@Pattern(regexp = Constants_Message.REGEXP_PASSWORD,message=Constants_Message.VALID_PASSWORD)
	private String request_password;

	@Pattern(regexp = Constants_Message.REGEXP_PHONE_NO,message=Constants_Message.VALID_PHONE_NO)
	private String request_phone_no;
	
	private String request_role;
	
	private long request_id;


}
