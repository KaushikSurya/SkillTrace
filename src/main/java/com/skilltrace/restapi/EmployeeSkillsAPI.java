package com.skilltrace.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrace.model.EmployeeSkills;
import com.skilltrace.service.EmployeeSkillsService;

@RestController
@CrossOrigin
@RequestMapping(value = "/empSkills")
public class EmployeeSkillsAPI {
	
	@Autowired
	private EmployeeSkillsService empSkillsService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeSkills>> getAllEmployeeSkills(){
		
		ResponseEntity<List<EmployeeSkills>> resp = null;
		List<EmployeeSkills> employeeSkills = empSkillsService.getAllEmployeeSkills();
		if(employeeSkills!=null)
			resp = new ResponseEntity<List<EmployeeSkills>>(employeeSkills, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<EmployeeSkills>>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<List<EmployeeSkills>> getEmployeeSkillsById(@PathVariable("employeeId") long employeeId){
		
		ResponseEntity<List<EmployeeSkills>> resp = null;
		List<EmployeeSkills> employeeSkills = empSkillsService.findByEmployeeId(employeeId);
		if(employeeSkills!=null)
			resp = new ResponseEntity<List<EmployeeSkills>>(employeeSkills, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<EmployeeSkills>>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<EmployeeSkills>> getAllConnections(
			@PathVariable("field") String fieldBy,
			@PathVariable("srchValue") String searchValue){
		
		ResponseEntity<List<EmployeeSkills>> resp = null;
		
		switch(fieldBy) {
			case "employeeId":
				List<EmployeeSkills> resultsByEmployeeId = empSkillsService.findByEmployeeId(Long.parseLong(searchValue));
				if(resultsByEmployeeId!=null) {
					resp = new ResponseEntity<>(resultsByEmployeeId, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "employeeName":
				List<EmployeeSkills> resultsByName = empSkillsService.findByEmployeeName(searchValue);
				if(resultsByName!=null && resultsByName.size()!=0) {
					resp = new ResponseEntity<>(resultsByName, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				break;
			case "skillId":
				List<EmployeeSkills> resultsBySkillId = empSkillsService.findBySkillId(Long.parseLong(searchValue));
				if(resultsBySkillId!=null) {
					resp = new ResponseEntity<>(resultsBySkillId, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;	
			case "skillName":
				List<EmployeeSkills> resultsBySkillName = empSkillsService.findBySkillName(searchValue);
				if(resultsBySkillName!=null) {
					resp = new ResponseEntity<>(resultsBySkillName, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "level":
				List<EmployeeSkills> resultsByLevel = empSkillsService.findByLevel(Integer.parseInt(searchValue));
				if(resultsByLevel!=null && resultsByLevel.size()!=0) {
					resp = new ResponseEntity<>(resultsByLevel, HttpStatus.OK);
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
	
	@GetMapping("/{employeeId}/{skillName}/{srchValue}")
	public ResponseEntity<List<EmployeeSkills>> getMySearchedSkills(
			@PathVariable("employeeId") long employeeId,
			@PathVariable("skillName") String field,
			@PathVariable("srchValue") String searchValue){
		
		ResponseEntity<List<EmployeeSkills>> resp = null;
		
		List<EmployeeSkills> employeeSkills = empSkillsService.findByEmployeeId(employeeId);
		List<EmployeeSkills> searchedSkills = new ArrayList<EmployeeSkills>();
		
		for(EmployeeSkills empSkill : employeeSkills) {
			if(empSkill.getSkillName().equalsIgnoreCase(searchValue))
				searchedSkills.add(empSkill);
		}
		
		if(searchedSkills!=null && resp == null)
			resp = new ResponseEntity<List<EmployeeSkills>>(searchedSkills, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<EmployeeSkills>>(HttpStatus.NOT_FOUND);
		
		return resp;
	}	
	
	@PostMapping
	public ResponseEntity<EmployeeSkills> addEmployeeSkills(@RequestBody EmployeeSkills employeeSkill){
		
		ResponseEntity<EmployeeSkills> resp = null;
		if(empSkillsService.existsByEmployeeSkillsId(employeeSkill.getEmployeeSkillsId())==false) {
			resp = new ResponseEntity<EmployeeSkills>(empSkillsService.addEmployeeSkills(employeeSkill), HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<EmployeeSkills> updateEmployeeSkills(@RequestBody EmployeeSkills employeeSkill){
		
		ResponseEntity<EmployeeSkills> resp = null;
		EmployeeSkills e = empSkillsService.getEmployeeSkillsById(employeeSkill.getEmployeeSkillsId());
		
		if(resp==null) {
			e = empSkillsService.updateEmployeeSkills(employeeSkill);
			if(e==null)
				resp = new ResponseEntity<EmployeeSkills>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<EmployeeSkills>(e, HttpStatus.OK);
		}
		
		return resp;
	}
	
	@DeleteMapping("/{employeeSkillsId}")
	public ResponseEntity<Void> deleteEmployeeSkills(@PathVariable("employeeSkillsId") long employeeSkillsId){
		
		ResponseEntity<Void> resp = null;
		if(empSkillsService.existsByEmployeeSkillsId(employeeSkillsId)==true) {
			empSkillsService.deleteEmployeeSkillsById(employeeSkillsId);
			resp = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);		
		return resp;
	}
	
	
}
