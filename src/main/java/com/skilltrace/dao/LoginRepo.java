package com.skilltrace.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.LoginCredentials;
import com.skilltrace.model.Role;

@Repository
public interface LoginRepo extends JpaRepository<LoginCredentials, Long> {
	
	List<LoginCredentials> findAllByRole(Role role);
	LoginCredentials findByUserName(String userName);

}
