package com.ss.security.config;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ss.entity.User_Entity;
import com.ss.repository.User_Repository;


@Component
public class Custom_User_Service implements UserDetailsService {

	@Autowired
	private User_Repository user_Repository;

	@Override
	public Custom_User_Details loadUserByUsername(String username) {

		User_Entity user_Entity = user_Repository.findByUserName(username);
		return new Custom_User_Details(user_Entity);
	}

}
