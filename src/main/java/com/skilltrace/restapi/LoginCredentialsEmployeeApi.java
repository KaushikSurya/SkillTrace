package com.skilltrace.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrace.model.Employee;
import com.skilltrace.model.LoginCredentials;
import com.skilltrace.model.Role;
import com.skilltrace.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/employeelogin")
public class LoginCredentialsEmployeeApi {
	@Autowired
	private LoginService us;
	
	/*@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	@GetMapping
	public ResponseEntity<List<LoginCredentials>> getAllLoginCredentialss() {
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);

	}*/
	//@Secured({"ROLE_ADMIN"})
	
	//@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	@GetMapping("/{id}")
	public ResponseEntity<LoginCredentials> getLoginCredentialsByUid(@PathVariable("id") long uid) {
		ResponseEntity<LoginCredentials> resp;
		LoginCredentials u = us.getUserById(uid);
		if (u == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(u, HttpStatus.OK);
		return resp;
	}
	
	/*@PreAuthorize("hasRole('ROLE_MANAGER')")
	@GetMapping("/manager/{id}")
	public ResponseEntity<List<LoginCredentials>> getLoginCredentialsByMgrId(@PathVariable("id") long mid) {
		ResponseEntity<List<LoginCredentials>> resp;
		List<LoginCredentials> u = us.getUserByM(mid);
		if (u == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(u, HttpStatus.OK);
		return resp;
	}*/
	
//	@GetMapping("/Emgr/{srchValue}")
//	public ResponseEntity<List<LoginCredentialss>> getAllRnames (
//		@PathVariable("srchValue") long mid)
//	{
//		ResponseEntity<List<LoginCredentialss>> resp;
//			
//			
//				List<LoginCredentialss> results =us.findAllByMid(mid);
//				if(results!=null && results.size()!=0){
//					
//					resp=new ResponseEntity<>(results,HttpStatus.OK);}
//				else {
//					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
//	
//		
//		return resp;
//	}
	
	
	
	@PostMapping
	public ResponseEntity<LoginCredentials> addEmployee(@RequestBody LoginCredentials login){
		
		ResponseEntity<LoginCredentials> resp = null;
		if(!us.existsById(login.getUserId())) {
			login.setRole(Role.EMPLOYEE);
			//login.setToken(token);
			resp = new ResponseEntity<LoginCredentials>(us.addUser(login), HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return resp;
	}
}
