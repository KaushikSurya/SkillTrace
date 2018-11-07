package com.skilltrace.service;

import java.util.List;

import com.skilltrace.model.LoginCredentials;
import com.skilltrace.model.Role;

public interface LoginService {
	
	public List<LoginCredentials> getAllUsers();
	public LoginCredentials getUserById(Long userId);
	public LoginCredentials getUserByName(String userName);
	public LoginCredentials addUser(LoginCredentials login);
	public List<LoginCredentials> getAllByRole(Role role);
	public LoginCredentials findByUserName(String userName);
	public boolean existsById(long userId);
	
}