package com.login_RegisterService.controller;

import org.springframework.web.bind.annotation.RestController;

import com.login_RegisterService.entity.UserCreadincial;
import com.login_RegisterService.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class HomeController {
	@Autowired private  UserService userService ; 

	@GetMapping("/Login") 
	public ResponseEntity<Map<String, Object>> chackLogin(@RequestBody UserCreadincial user) {
		Map<String, Object> map= new HashMap<>();
		map.put("user", userService.getUserCreadincialByEmailAndPassword(user.getEmail(), user.getPassword()));
		map.put("massage", "User Login Seccessfully");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/getUserByUserName/{username}")
	public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username){
			Map<String, Object> map= new HashMap<>();
				map.put("user", userService.getUserByUsername(username));
				map.put("massage", "User Loded Done");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatusCode.valueOf(200));
	}
	
	
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Map<String, Object>> deleteUserByUsername(@PathVariable String username){
			Map<String, Object> map= new HashMap<>();
			map.put("value", userService.deleteUserCreadincialByUsername(username));
			map.put("massage", "User Deleted");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatusCode.valueOf(200));
	}
	
	
	@PostMapping("/saveUser")
	public ResponseEntity<Map<String, Object>> saveUserCreadincial(@RequestBody UserCreadincial user) {
		Map<String, Object> map= new HashMap<>();
		  UserCreadincial saveUser = userService.saveUser(user);
		  map.put("user", saveUser);
		  map.put("massage", "User save Done  ");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatusCode.valueOf(201));
	}
	
	
}




