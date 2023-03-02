package com.ss.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ss.constant.Constants_Message;
import com.ss.email.Email_Send;
import com.ss.entity.User_Entity;
import com.ss.exception.Bad_Credentials_Exception_Handle;
import com.ss.exception.Empty_Result_Data_Access_Exception;
import com.ss.exception.Internal_Exception;
import com.ss.exception.No_Such_Element_Exception;
import com.ss.exception.Null_Value_Exception;
import com.ss.exception.Property_InvalidDataAccessApiUsageException;
import com.ss.repository.User_Repository;
import com.ss.request.PaginationRequest;
import com.ss.request.User_Request;
import com.ss.response.General_Response;
import com.ss.response.PaginationResponse;
import com.ss.security.config.Custom_User_Details;


@Service
public class User_Service_Impl implements User_Service
{
	private final Logger log = LoggerFactory.getLogger(User_Service_Impl.class);

	@Autowired
	private User_Repository user_Repository;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	Email_Send email_Send;
	
	@Override
	public ResponseEntity<General_Response> user_register(User_Request user_Request)
	{
		
	try 
	{
		User_Entity user_entity=new User_Entity(user_Request.getRequest_email(),user_Request.getRequest_first_name(),user_Request.getRequest_last_name(),passwordEncoder.encode(user_Request.getRequest_password()),user_Request.getRequest_phone_no(),Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())),"ROLE_"+user_Request.getRequest_role().toUpperCase());
		user_entity.setPassword_updated_at(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
		log.info("DATA SAVE SUCCESSFULLY"+user_Request);
		
		return  new ResponseEntity<General_Response>(new General_Response(Constants_Message.SAVE_SUCCESSFULLY,user_Repository.save(user_entity)),HttpStatus.OK);
	}
	catch (Exception e)
	{
		log.error(e.getLocalizedMessage());
		
		throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
	}

}

	@Override
	public ResponseEntity<General_Response> user_get(PaginationRequest paginationRequest) {
		
		if (!paginationRequest.getSortBy().equals("email") && !paginationRequest.getSortBy().equals("role")
				&& !paginationRequest.getSortBy().equals("id")
				&& !paginationRequest.getSortBy().equals("first_name")
				&& !paginationRequest.getSortBy().equals("last_name")) {
			throw new Property_InvalidDataAccessApiUsageException(Constants_Message.VALID_PROPERTY_SORT_BY);
		}
		
		try 
		{
			Page<User_Entity> pageUser =null;
			
			Pageable p = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize(),Sort.by(paginationRequest.getSortBy()));
			
			if(paginationRequest.getSearch()==null)		
			{
				pageUser = user_Repository.findAll(p);	
			}
			else
			{
			pageUser = user_Repository.search(paginationRequest.getSearch(),paginationRequest.getSearch(),paginationRequest.getSearch(),paginationRequest.getSearch(), p); 
			}
			
			List<User_Entity> user_entity_list = pageUser.getContent();
			
			PaginationResponse paginationResponse = new PaginationResponse(pageUser.getNumber(), pageUser.getSize(),pageUser.getTotalPages(), pageUser.getTotalElements(), pageUser.isLast(), pageUser.isFirst());
			
				log.info("DATA FOUND SUCCESSFULLY"+"USER SERVICE CLASS CALLED");
				
				List list=new ArrayList<>();
				list.add(paginationResponse);
				list.add(user_entity_list);
				
			return new ResponseEntity<General_Response>(new General_Response(Constants_Message.FOUND_SUCCESSFULLY,list),HttpStatus.FOUND);
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
			
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);	
		}
		
		}

	@Override
	public ResponseEntity<General_Response> user_update(User_Request user_Request) {
	

		String password=null;
		try
		{
			 password = user_Repository.findById(user_Request.getRequest_id()).get().getPassword();
		
		}
		catch (Exception e) {
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
		
		if(!passwordEncoder.matches(user_Request.getRequest_password(),password))
		{
			throw new Bad_Credentials_Exception_Handle(Constants_Message.PASSWORD_NOT_CHANGE);
		}
		try 
		{
			User_Entity user_entity=new User_Entity(user_Request.getRequest_id(),user_Request.getRequest_email(),user_Request.getRequest_first_name(),user_Request.getRequest_last_name(),passwordEncoder.encode(user_Request.getRequest_password()),Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())),user_Request.getRequest_phone_no(),"ROLE_"+user_Request.getRequest_role().toUpperCase());
			
			log.info("DATA UPDATE SUCCESSFULLY"+user_Request);
			
			return  new ResponseEntity<General_Response>(new General_Response(Constants_Message.UPDATE_SUCCESSFULLY,user_Repository.save(user_entity)),HttpStatus.OK);
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
			
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public ResponseEntity<General_Response> user_delete(long request_id) {
		try
		{
		user_Repository.deleteById(request_id);
		return new ResponseEntity<General_Response>(new General_Response(Constants_Message.DELETE_SUCCESSFULLY),HttpStatus.OK);
		}
		catch (EmptyResultDataAccessException e) 
		{
			throw new Empty_Result_Data_Access_Exception(Constants_Message.DELETE_ID_NOT_FOUND);
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
			
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public ResponseEntity<General_Response> user_getbyid(long request_id) {
		try
		{
		User_Entity user_entity = user_Repository.findById(request_id).get();
		return new ResponseEntity<General_Response>(new General_Response(Constants_Message.FOUND_SUCCESSFULLY,user_entity),HttpStatus.OK);
		}
		catch (NoSuchElementException e)
		{
			log.error(e.getMessage());
			throw new No_Such_Element_Exception(Constants_Message.ID_NOT_FOUND);
		}
		
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
			
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public ResponseEntity<General_Response> user_get_po() {
		try
		{
		List<User_Entity> user_entity = user_Repository.findAll().stream().filter(e->e.getRole().equals("ROLE_PO")).collect(Collectors.toList());
	
		return new ResponseEntity<General_Response>(new General_Response(Constants_Message.FOUND_SUCCESSFULLY,user_entity),HttpStatus.OK);
		}	
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
		
	}

	@Override
	public ResponseEntity<General_Response> user_delete_po(long request_id) {
		try
		{
		User_Entity user_Entity = user_Repository.findById(request_id).get();
		
		if(user_Entity.getRole().equals("ROLE_PO"))
		{
			user_Repository.deleteById(request_id);
			return new ResponseEntity<General_Response>(new General_Response(Constants_Message.DELETE_SUCCESSFULLY),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<General_Response>(new General_Response(Constants_Message.DELETE_ID_NOT_FOUND),HttpStatus.BAD_REQUEST);
		}
		}
		catch (NoSuchElementException e)
		{
			log.error(e.getMessage());
			throw new No_Such_Element_Exception(Constants_Message.ID_NOT_FOUND);
		}
		
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
			
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
		
	}

	@Override
	public ResponseEntity<General_Response> user_update_po(User_Request user_Request)
	{
		User_Entity user_Entity =null;
		try {
			 user_Entity = user_Repository.findById(user_Request.getRequest_id()).get();
		}
		catch (NoSuchElementException e)
		{
			log.error(e.getMessage());
			throw new No_Such_Element_Exception(Constants_Message.ID_NOT_FOUND);
		}
		if(!passwordEncoder.matches(user_Request.getRequest_password(),user_Entity.getPassword()))
		{
			throw new Bad_Credentials_Exception_Handle(Constants_Message.PASSWORD_NOT_CHANGE);
		}
		try
		{
		
		if(user_Entity.getRole().equals("ROLE_PO"))
		{
			User_Entity user= new User_Entity(user_Request.getRequest_email(), user_Request.getRequest_first_name(), user_Request.getRequest_last_name(), Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())), user_Request.getRequest_phone_no());
			 user.setCreated_at(user_Entity.getCreated_at());
			 user.setId(user_Entity.getId());
			 user.setRole(user_Entity.getRole());
			 user.setPassword(user_Entity.getPassword());
			 return new ResponseEntity<General_Response>(new General_Response(Constants_Message.UPDATE_SUCCESSFULLY,user_Repository.save(user)),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<General_Response>(new General_Response(Constants_Message.VALID_ROLE_AND_ID),HttpStatus.BAD_REQUEST);
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
		
		
	}

	@Override
	public ResponseEntity<General_Response> get_profile(long request_id)
	
	{
		Authentication authentication=null;
		
		User_Entity user_Entity=null;
		
		try 
		{
			authentication = SecurityContextHolder. getContext(). getAuthentication(); 
			
			user_Entity = user_Repository.findById(request_id).get();
			
		}
		catch (Exception e) {

			throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);

		}
		
				
		if(authentication.getName().equals(user_Entity.getEmail()))
		{
			return new ResponseEntity<General_Response>(new General_Response(Constants_Message.FOUND_SUCCESSFULLY,user_Entity),HttpStatus.OK);
		}
		else
		{
			throw new Empty_Result_Data_Access_Exception(Constants_Message.ID_NOT_FOUND);
		}
		
		
	}

	@Override
	public ResponseEntity<General_Response> update_profile(User_Request user_Request) {
		
		{
			
			try 
			{
				Authentication authentication = SecurityContextHolder. getContext(). getAuthentication(); 
				
				User_Entity	user_Entity = user_Repository.findById(user_Request.getRequest_id()).get();
				if(authentication.getName().equals(user_Entity.getEmail()))
				{
					User_Entity user=new User_Entity(user_Request.getRequest_id(),user_Request.getRequest_email(),user_Request.getRequest_first_name(),user_Request.getRequest_last_name(),passwordEncoder.encode(user_Request.getRequest_password()),Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())),user_Request.getRequest_phone_no());
					user.setRole(user_Entity.getRole());
					user.setCreated_at(user_Entity.getCreated_at());
					if(!passwordEncoder.matches(user_Request.getRequest_password(),user_Entity.getPassword()))
					{
						user.setPassword_updated_at(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
                       email_Send.send(user_Entity.getEmail(),Constants_Message.EMAIL_PASSWORD_CHANGE +" "+user_Entity.getFirst_name()+" "+ user_Entity.getLast_name());					
					}
					return new ResponseEntity<General_Response>(new General_Response(Constants_Message.UPDATE_SUCCESSFULLY,user_Repository.save(user)),HttpStatus.OK);
				}
				else
				{
					throw new Empty_Result_Data_Access_Exception(Constants_Message.ID_NOT_FOUND);
				}
				
			}
			catch (Exception e) {

				throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);

			}			
			
		}
	}
}

