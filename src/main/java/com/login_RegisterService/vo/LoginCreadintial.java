package com.login_RegisterService.vo;

public class LoginCreadintial {
	private String email;
	private String password;
	private String roll;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginCreadintial [email=" + email + ", password=" + password + "]";
	}
	public LoginCreadintial(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginCreadintial() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
