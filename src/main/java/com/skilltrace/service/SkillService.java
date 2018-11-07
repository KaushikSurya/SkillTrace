package com.skilltrace.service;

import java.util.List;

import com.skilltrace.model.Skill;

public interface SkillService {

	Skill addSkill(Skill skill);
	Skill updateSkill(Skill skill);
	boolean deleteSkillById(long skillId);
	Skill getSkillById(long skillId);
	List<Skill> getAllSkills();
	
	public double calculateFinalScore(long employeeId);
	
	List<Skill> findBySkillName(String skillName);
	List<Skill> findByScore(double score);
	List<Skill> findBySpecialization(String specialization);
	
	boolean existsBySkillId(long skillId);
}
