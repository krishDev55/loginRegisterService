package com.login_RegisterService.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login_RegisterService.entity.UserCreadincial;
import com.login_RegisterService.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
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
		try {
			UserCreadincial first = userRepo
						.findUserCreadincialByEmailAndPassword(email,password)
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
	
	
	
	
}
