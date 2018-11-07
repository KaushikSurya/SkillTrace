package com.skilltrace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.CourseEmployee;

@Repository
public interface CourseEmployeeRepo extends JpaRepository<CourseEmployee, Long>{

}
