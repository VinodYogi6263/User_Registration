package com.ss.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ss.constant.Constants_Message;
import com.ss.request.PaginationRequest;
import com.ss.request.User_Request;
import com.ss.response.General_Response;
import com.ss.service.User_Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
public class User_Controller {

	private final Logger log = LoggerFactory.getLogger(User_Controller.class);
	
	@Autowired
	private User_Service user_Service;

	@Operation(summary = "User Registration", description = "User Registration all Role MANAGER APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.SAVE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/register")
	public ResponseEntity<General_Response> user_register(@RequestBody  @Valid  User_Request user_Request) 
	{
		return user_Service.user_register(user_Request);
	}
	
	@Operation(summary = "User find by id", description = "User find  by id all Role MANAGER APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.FOUND_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/getbyid")
	public ResponseEntity<General_Response> user_getbyid( @RequestParam("id") long id)
	{
		return user_Service.user_getbyid(id);
		
	}
	
	@Operation(summary = "User find all ", description = "User  all Role MANAGER APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.SAVE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/get")
	public ResponseEntity<General_Response> user_get(@RequestBody @Valid PaginationRequest paginationRequest)
	{
		return user_Service.user_get(paginationRequest);
		
	}

	@Operation(summary = "User Update", description = "User Update all Role MANAGER APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.UPDATE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasRole('MANAGER')")
	@PutMapping("/update")
	public ResponseEntity<General_Response> user_update(@RequestBody @Valid User_Request user_Request)
	{
		return user_Service.user_update(user_Request);
		
	}
	
	@Operation(summary = "User Delet", description = "User Delete all Role MANAGER APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.DELETE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasRole('MANAGER')")
	@DeleteMapping("/delete")
	public ResponseEntity<General_Response> user_delete(@RequestParam ("request_id") long request_id)
	{
		return user_Service.user_delete(request_id);
		
	}
	
	@Operation(summary = "APPRAISER get po ", description = "APPRAISER get  Role  APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.FOUND_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasRole('APPRAISER')")
	@GetMapping("/get_po")
	public ResponseEntity<General_Response> user_get_op()
	{
		return user_Service.user_get_po();
		
	}
	
	@Operation(summary = "APPRAISER Delete po ", description = "APPRAISER Delete  Role  APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.DELETE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	
	@PreAuthorize("hasRole('APPRAISER')")
	@DeleteMapping("/delete_po")
	public ResponseEntity<General_Response> user_delete_po(@RequestParam ("id") long request_id)
	{
		return user_Service.user_delete_po(request_id);
		
	}
	
	@Operation(summary = "APPRAISER Update po ", description = "APPRAISER Update  Role  APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.UPDATE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	
	@PreAuthorize("hasRole('APPRAISER')")
	@PutMapping("/update_po")
	public ResponseEntity<General_Response> user_update_po(@RequestBody @Valid User_Request user_Request)
	{
		return user_Service.user_update_po(user_Request);
		
	}
	
	
	@Operation(summary = "APPRAISER Register po ", description = "APPRAISER Register  Role  APPRAISER PO")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.SAVE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	
	@PreAuthorize("hasRole('APPRAISER')")
	@PostMapping("/register_op")
	public ResponseEntity<General_Response> user_op(@RequestBody  @Valid  User_Request user_Request) 
	{
		int flag = user_Request.getRequest_role().compareToIgnoreCase("po");
		if(flag==0)
		{
			return user_Service.user_register(user_Request);
		}
		else
		{
			return new ResponseEntity<General_Response>(new General_Response(Constants_Message.VALID_ROLE),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@Operation(summary = "User get person profile", description = "User get person profile")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.FOUND_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasAnyRole('APPRAISER','MANAGER','PO')")
	@GetMapping("/profile")
	public ResponseEntity<General_Response> get_profile(@RequestParam("id") long request_id) 
	{
		return user_Service.get_profile(request_id);
	
	}
	
	@Operation(summary = "User Update person profile", description = "User Update person profile")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Constants_Message.UPDATE_SUCCESSFULLY),
			@ApiResponse(responseCode = "400", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Constants_Message.INTERNAL_EXCEPTION_MESSAGE, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	
	@PreAuthorize("hasAnyRole('APPRAISER','MANAGER','PO')")
	@PutMapping("/profile_update")
	public ResponseEntity<General_Response> update_profile(@RequestBody @Valid User_Request user_Request) 
	{
		return user_Service.update_profile(user_Request);
	
	}
	
	
	
	
}
