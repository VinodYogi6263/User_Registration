package com.ss.security.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ss.entity.User_Entity;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class Custom_User_Details implements UserDetails {

	private User_Entity user_Entity ;

	public Custom_User_Details(User_Entity user_Entity) 
	{
		this.user_Entity=user_Entity;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(user_Entity.getRole()));
		return set;

	}

	@Override
	public String getPassword() {
		return user_Entity.getPassword();
	}

	@Override
	public String getUsername() {
		return user_Entity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
