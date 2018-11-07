package com.skilltrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.dao.EmployeeSkillsRepository;
import com.skilltrace.dao.SkillRepository;
import com.skilltrace.model.EmployeeSkills;
import com.skilltrace.model.Skill;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private EmployeeSkillsRepository empSkillRepo;
	
	@Override
	public Skill addSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	@Override
	public Skill updateSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	@Override
	public boolean deleteSkillById(long skillId) {
		boolean isDeleted= false;
		if(skillRepo.existsById(skillId)) {
			skillRepo.deleteById(skillId);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public Skill getSkillById(long skillId) {
		Optional<Skill> optSkill = skillRepo.findById(skillId);
		return optSkill!=null?optSkill.get():null;
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillRepo.findAll();
	}

	@Override
	public List<Skill> findBySkillName(String skillName) {
		return skillRepo.findBySkillName(skillName);
	}

	@Override
	public List<Skill> findByScore(double score) {
		return skillRepo.findByScore(score);
	}

	@Override
	public List<Skill> findBySpecialization(String specialization) {
		return skillRepo.findBySpecialization(specialization);
	}

	@Override
	public boolean existsBySkillId(long skillId) {
		return skillRepo.existsBySkillId(skillId);
	}

	@Override
	public double calculateFinalScore(long employeeId) {
		double finalScore = 0;
		int totalLevel = 0;
		double avgLevel = 0;
		int totalScore = 0;
		double avgScore = 0.0;
		List<Skill> skills = getAllSkills();
		List<EmployeeSkills> empSkills = empSkillRepo.findByEmployeeId(employeeId);
		for (int i = 0; i < empSkills.size(); i++) {
			EmployeeSkills empSkill1 = empSkills.get(i);
			totalLevel += empSkill1.getLevel();
			for (int j = 0; j < skills.size(); j++) {
				Skill skill = skills.get(j);
				if(skill.getSkillId()==empSkill1.getSkillId()) {
					totalScore += skill.getScore();
			}
			}
		}
		avgLevel = totalLevel / empSkills.size();
		avgScore = totalScore / empSkills.size();
		finalScore = (2 * avgLevel + 3 * avgScore)/5;
		return finalScore;
	}

}
