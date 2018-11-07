package com.skilltrace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.ManagerRequest;

@Repository
public interface ManagerRequestRepo extends JpaRepository<ManagerRequest, Long> {

}
