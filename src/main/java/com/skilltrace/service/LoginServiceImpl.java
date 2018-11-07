package com.skilltrace.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skilltrace.model.LoginCredentials;
import com.skilltrace.model.Role;
import com.skilltrace.dao.LoginRepo;

@Service(value="loginService")
public class LoginServiceImpl implements LoginService, UserDetailsService {
	
	@Autowired
	public LoginRepo loginRepo;
	
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public List<LoginCredentials> getAllUsers() {
		return loginRepo.findAll();
	}

	@Override
	public LoginCredentials getUserById(Long userId) {
		Optional<LoginCredentials> opt = loginRepo.findById(userId);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public LoginCredentials getUserByName(String userName) {
		LoginCredentials login = loginRepo.findByUserName(userName);
		return login;
	}

	@Override
	public LoginCredentials addUser(LoginCredentials login) {
		loginRepo.save(login);
		return login;
	}

	@Override
	public List<LoginCredentials> getAllByRole(Role role) {
		return loginRepo.findAllByRole(role);
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(LoginCredentials login) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
            authorities.add(new SimpleGrantedAuthority("ROLE_" + login.getRole().toString()));
		logger.info("role::"+authorities.iterator().next().getAuthority().toString());
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("email     "+userName);
		LoginCredentials login = loginRepo.findByUserName(userName);
		if(login == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(login.getUserName(), login.getPassword(), getAuthority(login));
	}

	@Override
	public LoginCredentials findByUserName(String userName) {
		return loginRepo.findByUserName(userName);
	}

	@Override
	public boolean existsById(long userId) {
		return loginRepo.existsById(userId);
	}
	
	
}
