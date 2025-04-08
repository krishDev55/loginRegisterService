package com.login_RegisterService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserCreadincial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String email;
	private String password;
	private long mobileNo;
	
	private String roll;
	
	
	
	public int getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getRoll() {
		return roll;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	
	
	
	
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	@Override
	public String toString() {
		return "UserCreadincial [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", mobileNo=" + mobileNo + ", roll=" + roll + "]";
	}
	
	
	public UserCreadincial() {
		super();

	}
	
	
	public UserCreadincial(int id, String username, String email, String password, long mobileNo, String roll) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.roll = roll;
	}

	
	
	
	
	
	
	
	
	
}
