package com.skilltrace.restapi;

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

import com.skilltrace.model.EmployeeSkills;
import com.skilltrace.model.Skill;
import com.skilltrace.model.SkillRequest;
import com.skilltrace.service.SkillRequestService;

@RestController
@CrossOrigin
@RequestMapping(value = "/skillRequests")
public class SkillRequestAPI {
	
	@Autowired
	private SkillRequestService skillRequestService;
	
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')")
	@GetMapping
	public ResponseEntity<List<SkillRequest>> getAllSkillRequests(){
		
		ResponseEntity<List<SkillRequest>> resp = null;
		List<SkillRequest> skillRequests = skillRequestService.getAllSkillRequests();
		if(skillRequests!=null)
			resp = new ResponseEntity<List<SkillRequest>>(skillRequests, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<SkillRequest>>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')")
	@GetMapping("/{requestId}")
	public ResponseEntity<SkillRequest> getSkillRequestById(@PathVariable("requestId") long requestId){
		
		ResponseEntity<SkillRequest> resp = null;
		SkillRequest skillRequest = skillRequestService.getSkillRequestById(requestId);
		if(skillRequest!=null)
			resp = new ResponseEntity<SkillRequest>(skillRequest, HttpStatus.OK);
		else
			resp = new ResponseEntity<SkillRequest>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')")
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<SkillRequest>> getAllConnections(
			@PathVariable("field") String fieldBy,
			@PathVariable("srchValue") String searchValue){
		
		ResponseEntity<List<SkillRequest>> resp = null;
		
		switch(fieldBy) {
			case "employeeId":
				List<SkillRequest> resultsByEmployeeId = skillRequestService.findByEmployeeId(Long.parseLong(searchValue));
				if(resultsByEmployeeId!=null) {
					resp = new ResponseEntity<>(resultsByEmployeeId, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "employeeName":
				List<SkillRequest> resultsByEmployeeName = skillRequestService.findByEmployeeName(searchValue);
				if(resultsByEmployeeName!=null && resultsByEmployeeName.size()!=0) {
					resp = new ResponseEntity<>(resultsByEmployeeName, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				break;
			case "skillRequestedId":
				List<SkillRequest> resultsBySkillId = skillRequestService.findBySkillRequestedId(Long.parseLong(searchValue));
				if(resultsBySkillId!=null) {
					resp = new ResponseEntity<>(resultsBySkillId, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "skillRequestedName":
				List<SkillRequest> resultsBySkillName = skillRequestService.findBySkillRequestedName(searchValue);
				if(resultsBySkillName!=null) {
					resp = new ResponseEntity<>(resultsBySkillName, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "status":
				List<SkillRequest> resultsByStatus = skillRequestService.findByStatus(searchValue);
				if(resultsByStatus!=null) {
					resp = new ResponseEntity<>(resultsByStatus, HttpStatus.OK);
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
	public ResponseEntity<SkillRequest> addSkillRequest(@RequestBody SkillRequest skillRequest){
		
		ResponseEntity<SkillRequest> resp = null;
		if(!skillRequestService.existsById(skillRequest.getRequestId())) {
			resp = new ResponseEntity<SkillRequest>(skillRequestService.addSkillRequest(skillRequest), HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<SkillRequest> updateSkillRequest(@RequestBody SkillRequest skillRequest){
		
		ResponseEntity<SkillRequest> resp = null;
		if(skillRequestService.existsById(skillRequest.getRequestId()))
			resp = new ResponseEntity<SkillRequest>(skillRequestService.updateSkillRequest(skillRequest), HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@DeleteMapping("/{requestId}")
	public ResponseEntity<Void> deleteSkillRequest(@PathVariable("requestId") long requestId){
		
		ResponseEntity<Void> resp = null;
		if(skillRequestService.existsById(requestId)) {
			skillRequestService.deleteSkillRequestById(requestId);
			resp = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);		
		return resp;
	}
	
	
}
