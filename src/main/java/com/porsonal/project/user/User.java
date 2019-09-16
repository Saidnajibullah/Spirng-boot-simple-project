package com.porsonal.project.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class User {
	
	
	@NotNull(message="User id cannot be empty")
	private String userId;
	
	@NotNull(message="User firstname cannot be empty")
	private String firstname;
	
	@NotNull(message="User lastname cannot be empty")
	private String lastname;
	
	@NotNull(message="User email cannot be empty")
	@Email(message="Invalid email")
	private String email;
	public String getUserId() {
		return userId;
	}
	public void setUserID(String userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
