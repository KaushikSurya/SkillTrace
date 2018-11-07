package com.skilltrace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByEmployeeName(String employeeName);
	List<Employee> findByDepartment(String department);
	List<Employee> findByDesignation(String designation);
	Employee findByEmailId(String emailId);
	Employee findByMobileNumber(String mobileNumber);
	List<Employee> findByCompany(String company);
}
