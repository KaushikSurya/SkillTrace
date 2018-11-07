package com.skilltrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.dao.EmployeeSkillsRepository;
import com.skilltrace.model.EmployeeSkills;

@Service
public class EmployeeSkillsServiceImpl implements EmployeeSkillsService {
	
	@Autowired
	private EmployeeSkillsRepository empSkillsRepo;


	@Override
	public EmployeeSkills addEmployeeSkills(EmployeeSkills employeeSkills) {
		return empSkillsRepo.save(employeeSkills);
	}

	@Override
	public EmployeeSkills updateEmployeeSkills(EmployeeSkills employeeSkills) {
		return empSkillsRepo.save(employeeSkills);
	}

	@Override
	public boolean deleteEmployeeSkillsById(long employeeSkillsId) {
		boolean isDeleted = false;
		if(empSkillsRepo.existsById(employeeSkillsId)) {
			empSkillsRepo.deleteById(employeeSkillsId);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public EmployeeSkills getEmployeeSkillsById(long employeeSkillsId) {
		Optional<EmployeeSkills> optEmpSkills = empSkillsRepo.findById(employeeSkillsId);
		return optEmpSkills!=null?optEmpSkills.get():null;
	}

	@Override
	public List<EmployeeSkills> getAllEmployeeSkills() {
		return empSkillsRepo.findAll();
	}

	@Override
	public List<EmployeeSkills> findByEmployeeId(long employeeId) {
		return empSkillsRepo.findByEmployeeId(employeeId);
	}

	@Override
	public List<EmployeeSkills> findByEmployeeName(String employeeName) {
		return empSkillsRepo.findByEmployeeName(employeeName);
	}

	@Override
	public List<EmployeeSkills> findBySkillId(long skillId) {
		return empSkillsRepo.findBySkillId(skillId);
	}

	@Override
	public List<EmployeeSkills> findBySkillName(String skillName) {
		return empSkillsRepo.findBySkillName(skillName);
	}

	@Override
	public List<EmployeeSkills> findByLevel(int level) {
		return empSkillsRepo.findByLevel(level);
	}

	@Override
	public boolean existsByEmployeeSkillsId(long employeeSkillsId) {
		return empSkillsRepo.existsByEmployeeSkillsId(employeeSkillsId);
	}

	
}
