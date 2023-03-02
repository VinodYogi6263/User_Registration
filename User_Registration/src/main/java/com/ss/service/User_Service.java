package com.ss.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ss.request.PaginationRequest;
import com.ss.request.User_Request;
import com.ss.response.General_Response;

@Component
public interface User_Service 
{

	public ResponseEntity<General_Response> user_register(User_Request user_Request);
	
	public ResponseEntity<General_Response> user_get(PaginationRequest paginationRequest);
	
	public ResponseEntity<General_Response> user_update(User_Request user_Request);
	
	public ResponseEntity<General_Response> user_delete(long request_id);
	
	public ResponseEntity<General_Response> user_getbyid(long request_id);

	public ResponseEntity<General_Response> user_get_po();

	public ResponseEntity<General_Response> user_delete_po(long request_id);

	public ResponseEntity<General_Response> user_update_po(User_Request user_Request);

	public ResponseEntity<General_Response> get_profile(long request_id);

	public ResponseEntity<General_Response> update_profile(User_Request user_Request);

}
