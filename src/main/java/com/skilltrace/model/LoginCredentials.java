package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="login")
public class LoginCredentials {
	
	@Id
	private long userId;
	@NotEmpty(message="Should not be empty")
	private String userName;
	@NotEmpty(message="Should not be empty")
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
//	@NotEmpty(message="Should not be empty")
//	private String token;
	
	public LoginCredentials() {
		
	}


	public LoginCredentials(long userId, @NotEmpty(message = "Should not be empty") String userName,
			@NotEmpty(message = "Should not be empty") String password, Role role
			/*,@NotEmpty(message = "Should not be empty") String token*/) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
		//this.token = token;
	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}*/

	
	
}
