package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ss.constant.Constants_Message;
import com.ss.exception.Null_Value_Exception;
import com.ss.helper.Jwt_Util;
import com.ss.request.Jwt_Request;
import com.ss.response.General_Response;
import com.ss.security.config.Custom_User_Details;
import com.ss.security.config.Custom_User_Service;

@RequestMapping("/jwt")
@RestController
public class Generate_Token_Controller {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private Custom_User_Service userDetailsService;
	
	@Autowired
	private Jwt_Util jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<General_Response> generateToken(@RequestBody Jwt_Request jwtRequest) {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUser_name(), jwtRequest.getUser_password()));
		} catch (Exception e) {
			throw new Null_Value_Exception(Constants_Message.VALID_USER_NAME);
		}

		Custom_User_Details userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUser_name());
		String token = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<General_Response>(new General_Response(token), HttpStatus.OK);
	}

}
