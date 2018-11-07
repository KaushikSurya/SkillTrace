package com.skilltrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.dao.SkillRequestRepository;
import com.skilltrace.model.SkillRequest;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {
	
	@Autowired
	private SkillRequestRepository skillRequestRepo;

	@Override
	public SkillRequest addSkillRequest(SkillRequest skillRequest) {
		return skillRequestRepo.save(skillRequest);
	}

	@Override
	public SkillRequest updateSkillRequest(SkillRequest skillRequest) {
		return skillRequestRepo.save(skillRequest);
	}

	@Override
	public boolean deleteSkillRequestById(long requestId) {
		boolean isDeleted = false;
		if(skillRequestRepo.existsById(requestId)) {
			skillRequestRepo.deleteById(requestId);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public SkillRequest getSkillRequestById(long requestId) {
		Optional<SkillRequest> optSkillRequest = skillRequestRepo.findById(requestId);
		return optSkillRequest!=null?optSkillRequest.get():null;
	}

	@Override
	public List<SkillRequest> getAllSkillRequests() {
		return skillRequestRepo.findAll();
	}

	@Override
	public List<SkillRequest> findByEmployeeId(long employeeId) {
		return skillRequestRepo.findByEmployeeId(employeeId);
	}

	@Override
	public List<SkillRequest> findByEmployeeName(String employeeName) {
		return skillRequestRepo.findByEmployeeName(employeeName);
	}

	@Override
	public List<SkillRequest> findBySkillRequestedId(long skillRequestedId) {
		return skillRequestRepo.findBySkillRequestedId(skillRequestedId);
	}

	@Override
	public List<SkillRequest> findBySkillRequestedName(String skillRequestedName) {
		return skillRequestRepo.findBySkillRequestedName(skillRequestedName);
	}

	@Override
	public List<SkillRequest> findByStatus(String status) {
		return skillRequestRepo.findByStatus(status);
	}

	@Override
	public boolean existsById(long requestId) {
		return skillRequestRepo.existsById(requestId);
	}

}
