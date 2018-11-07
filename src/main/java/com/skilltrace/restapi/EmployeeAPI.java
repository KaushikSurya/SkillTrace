package com.skilltrace.restapi;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrace.model.Employee;
import com.skilltrace.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/employees")
public class EmployeeAPI {
	
	@Autowired
	private EmployeeService empService;
	
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')")
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		ResponseEntity<List<Employee>> resp = null;
		List<Employee> employees = empService.getAllEmployees();
		if(employees!=null)
			resp = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@PreAuthorize("hasRole('ROLE_GUEST')")
	@GetMapping("/guest")
	public ResponseEntity<List<Employee>> getAllIds(){
		
		ResponseEntity<List<Employee>> resp = null;
		List<Employee> employees = empService.getAllEmployees();
		for(Employee employee : employees) {
			employee.setCompany(null);
			employee.setDepartment(null);
			employee.setDesignation(null);
			employee.setEmailId(null);
			employee.setMobileNumber(null);
		}
		if(employees!=null)
			resp = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')")
	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") long employeeId){
		
		ResponseEntity<Employee> resp = null;
		Employee employee = empService.getEmployeeById(employeeId);
		if(employee!=null)
			resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else
			resp = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')")
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<Employee>> getAllConnections(
			@PathVariable("field") String fieldBy,
			@PathVariable("srchValue") String searchValue){
		
		ResponseEntity<List<Employee>> resp = null;
		
		switch(fieldBy) {
			case "mobileNumber":
				Employee eByMobNum = empService.findByMobileNumber(searchValue);
				if(eByMobNum!=null) {
					resp = new ResponseEntity<>(Collections.singletonList(eByMobNum), HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "emailId":
				Employee eByEmail = empService.findByEmailId(searchValue);
				if(eByEmail!=null) {
					resp = new ResponseEntity<>(Collections.singletonList(eByEmail), HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "employeeName":
				List<Employee> resultsByName = empService.findByEmployeeName(searchValue);
				if(resultsByName!=null && resultsByName.size()!=0) {
					resp = new ResponseEntity<>(resultsByName, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				break;
			case "department":
				List<Employee> resultsByDepartment = empService.findByDepartment(searchValue);
				if(resultsByDepartment!=null && resultsByDepartment.size()!=0) {
					resp = new ResponseEntity<>(resultsByDepartment, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				break;	
			case "designation":
				List<Employee> resultsByDesignation = empService.findByDesignation(searchValue);
				if(resultsByDesignation!=null && resultsByDesignation.size()!=0) {
					resp = new ResponseEntity<>(resultsByDesignation, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				break;
			case "company":
				List<Employee> resultsByCompany = empService.findByCompany(searchValue);
				if(resultsByCompany!=null && resultsByCompany.size()!=0) {
					resp = new ResponseEntity<>(resultsByCompany, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				break;
			default:
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		
		ResponseEntity<Employee> resp = null;
		if(!empService.existsById(employee.getEmployeeId())) {
			resp = new ResponseEntity<Employee>(empService.addEmployee(employee), HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		
		ResponseEntity<Employee> resp = null;
		Employee e = empService.getEmployeeById(employee.getEmployeeId());
		
		if(resp==null) {
			e = empService.updateEmployee(employee);
			if(e==null)
				resp = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Employee>(e, HttpStatus.OK);
		}
		
		return resp;
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") long employeeId){
		
		ResponseEntity<Void> resp = null;
		if(empService.existsById(employeeId)) {
			empService.deleteById(employeeId);
			resp = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);		
		return resp;
	}
	
}
