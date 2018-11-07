package com.skilltrace.service;

import java.util.List;

import com.skilltrace.model.EmployeeSkills;

public interface EmployeeSkillsService {

	EmployeeSkills addEmployeeSkills(EmployeeSkills employeeSkills);
	EmployeeSkills updateEmployeeSkills(EmployeeSkills employeeSkills);
	boolean deleteEmployeeSkillsById(long employeeSkillsId);
	EmployeeSkills getEmployeeSkillsById(long employeeSkillsId);
	List<EmployeeSkills> getAllEmployeeSkills();	

	List<EmployeeSkills> findByEmployeeId(long employeeId);
	List<EmployeeSkills> findByEmployeeName(String employeeName);
	List<EmployeeSkills> findBySkillId(long skillId);
	List<EmployeeSkills> findBySkillName(String skillName);
	List<EmployeeSkills> findByLevel(int level);
	
	boolean existsByEmployeeSkillsId(long employeeSkillsId);
}
