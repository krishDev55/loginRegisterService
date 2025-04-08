package com.login_RegisterService.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.login_RegisterService.entity.UserCreadincial;
import com.login_RegisterService.repository.UserRepository;
import com.login_RegisterService.security.service.MyJwtService;
import com.login_RegisterService.vo.LoginCreadintial;


@Service
public class UserService {
	
	@Autowired UserRepository userRepo;
	@Autowired AuthenticationManager authManager;
	
	@Autowired MyJwtService myJwtService;
	
	
	public UserCreadincial saveUser(UserCreadincial user) {
		try {
			 return userRepo.save(user);
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}
	
	
	
	public UserCreadincial getUserByEmail(String email) {
		try {
			UserCreadincial first = userRepo
						.findUserCreadincialByEmail(email)
			            .stream()
			            .findFirst().get();
			if(Optional.of(first).isPresent()) {
				return first;
			}
		 return null;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	public  UserCreadincial getUserCreadincialByEmailAndPassword(String email,String password) {
		
		List<UserCreadincial> list = userRepo.findUserCreadincialByEmailAndPassword(email,password);
		
		System.out.println("Temp \n "+list);
		try {
			UserCreadincial first = userRepo
						.findUserCreadincialByEmailAndPassword(email,password)
			            .stream()
			            .findFirst().get();
			System.out.println("text " + first);
			if(Optional.of(first).isPresent()) {
				return first;
			}
		 return null;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	public UserCreadincial getUserByUsername(String username) {
		try {
			UserCreadincial first = userRepo
						.findUserCreadincialByUsername(username)
			            .stream()
			            .findFirst().get();
			if(Optional.of(first).isPresent()) {
				return first;
			}
		 return null;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	public String deleteUserCreadincialByUsername(String username) {
		
			try {
				 int text= userRepo.deleteByUsername(username);
				 if(text>=0) {
					 return "User Are Deleted ";
				 }
				 else return "User Not Deleted";
			} catch (Exception e) {
			
			}
			return "Something Wrong";
	}
	
	
	
	public String varify(LoginCreadintial user ) {
		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		Authentication authenticate = 
						authManager.authenticate(
								userToken
									);
		System.out.println("MyTest "+ userToken);
		if(authenticate.isAuthenticated()) {
			return myJwtService.generateToken(user.getEmail()) ;
		}
		return "User not found....!!!";
	}
	
	
}
