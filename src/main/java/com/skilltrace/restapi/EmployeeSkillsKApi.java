package com.skilltrace.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrace.model.EmployeeSkills;
import com.skilltrace.service.EmployeeSkillsService;

@RestController
@CrossOrigin
@RequestMapping("/employeeskills")
public class EmployeeSkillsKApi {
	
	@Autowired
	private EmployeeSkillsService empSkillService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeSkills>> getAllSkills() {
		return new ResponseEntity<>(empSkillService.getAllEmployeeSkills(), HttpStatus.OK);
	}


	@GetMapping("/{employeeId}")
	public ResponseEntity<List<EmployeeSkills>> getEmployeeById(@PathVariable("employeeId") long employeeId) {
		ResponseEntity<List<EmployeeSkills>> resp;
		List<EmployeeSkills> empSkills = empSkillService.findByEmployeeId(employeeId);		
		if (empSkills == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			resp = new ResponseEntity<>(empSkills, HttpStatus.OK);
		}
			
		return resp;
	}
	
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<EmployeeSkills>> getAllPlans (
		@PathVariable("field") String fieldBy,
		@PathVariable("srchValue") String searchValue)
	{
		ResponseEntity<List<EmployeeSkills>> resp;
			switch(fieldBy){
			case "employeeName":
				List<EmployeeSkills> eben= empSkillService.findByEmployeeName(searchValue);
				if(eben!=null){
					resp=new ResponseEntity<>(eben,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
				
			case "skillName":				
				List<EmployeeSkills> ebsn= empSkillService.findBySkillName(searchValue);
				if(ebsn!=null){
					resp=new ResponseEntity<>(ebsn,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
				break;
				
			default:
				resp= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				break;	
		}
		
		return resp;
	}
	
	
}
