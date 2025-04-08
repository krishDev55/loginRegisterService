package com.login_RegisterService.security.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.login_RegisterService.entity.UserCreadincial;

@Service
public class UserCread implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserCreadincial user;

	public UserCread(UserCreadincial user) {
		this.user=user;
	}
	public UserCread() {
	
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// this Authority Only for User not Admin
		return Collections.singleton( 
					new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

}
