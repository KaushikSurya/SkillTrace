package com.skilltrace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.EmployeeSkills;

@Repository
public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Long> {

	List<EmployeeSkills> findByEmployeeId(long employeeId);
	List<EmployeeSkills> findByEmployeeName(String employeeName);
	List<EmployeeSkills> findBySkillId(long skillId);
	List<EmployeeSkills> findBySkillName(String skillName);
	List<EmployeeSkills> findByLevel(int level);
	
	boolean existsByEmployeeSkillsId(long employeeSkillsId);
}
