package com.skilltrace.service;

import java.util.List;

import com.skilltrace.model.SkillRequest;

public interface SkillRequestService {

	SkillRequest addSkillRequest(SkillRequest skillRequest);
	SkillRequest updateSkillRequest(SkillRequest skillRequest);
	boolean deleteSkillRequestById(long requestId);
	SkillRequest getSkillRequestById(long requestId);
	List<SkillRequest> getAllSkillRequests();
	
	List<SkillRequest> findByEmployeeId(long employeeId);
	List<SkillRequest> findByEmployeeName(String employeeName);
	List<SkillRequest> findBySkillRequestedId(long skillRequestedId);
	List<SkillRequest> findBySkillRequestedName(String skillRequestedName);
	List<SkillRequest> findByStatus(String status);
	
	boolean existsById(long requestId);
}
