package com.skilltrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.dao.EmployeeRepository;
import com.skilltrace.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public Employee addEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public boolean deleteById(long employeeId) {
		boolean isDeleted = false;
		if(empRepo.existsById(employeeId)) {
			empRepo.deleteById(employeeId);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		Optional<Employee> optEmp = empRepo.findById(employeeId);
		return optEmp!=null?optEmp.get():null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public List<Employee> findByEmployeeName(String employeeName) {
		return empRepo.findByEmployeeName(employeeName);
	}

	@Override
	public List<Employee> findByDepartment(String department) {
		return empRepo.findByDepartment(department);
	}

	@Override
	public List<Employee> findByDesignation(String designation) {
		return empRepo.findByDesignation(designation);
	}

	@Override
	public Employee findByEmailId(String emailId) {
		return empRepo.findByEmailId(emailId);
	}

	@Override
	public Employee findByMobileNumber(String mobileNumber) {
		return empRepo.findByMobileNumber(mobileNumber);
	}

	@Override
	public List<Employee> findByCompany(String company) {
		return empRepo.findByCompany(company);
	}

	@Override
	public boolean existsById(long employeeId) {
		return empRepo.existsById(employeeId);
	}

	
}
