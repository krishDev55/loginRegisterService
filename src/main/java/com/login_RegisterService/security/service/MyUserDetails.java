package com.login_RegisterService.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login_RegisterService.entity.UserCreadincial;
import com.login_RegisterService.repository.UserRepository;
import com.login_RegisterService.service.UserService;

@Service
public class MyUserDetails  implements UserDetailsService{
	
		@Autowired UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("this is the "+email);
		UserCreadincial user = userRepository
						.findUserCreadincialByEmail(email)
						.stream()
						.findFirst()
						.get()
						;
		return  new UserCread(user);
	}

}
