package com.skilltrace.service;

import java.util.List;

import com.skilltrace.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	boolean deleteById(long employeeId);
	Employee getEmployeeById(long employeeId);
	List<Employee> getAllEmployees();
	
	boolean existsById(long employeeId);
	
	List<Employee> findByEmployeeName(String employeeName);
	List<Employee> findByDepartment(String department);
	List<Employee> findByDesignation(String designation);
	Employee findByEmailId(String emailId);
	Employee findByMobileNumber(String mobileNumber);
	List<Employee> findByCompany(String company);
}
