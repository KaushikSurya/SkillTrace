package com.skilltrace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.SkillRequest;

@Repository
public interface SkillRequestRepository extends JpaRepository<SkillRequest, Long> {

	List<SkillRequest> findByEmployeeId(long employeeId);
	List<SkillRequest> findByEmployeeName(String employeeName);
	List<SkillRequest> findBySkillRequestedId(long skillRequestedId);
	List<SkillRequest> findBySkillRequestedName(String skillRequestedName);
	List<SkillRequest> findByStatus(String status);
}
