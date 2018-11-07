package com.skilltrace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

	List<Skill> findBySkillName(String skillName);
	List<Skill> findByScore(double score);
	List<Skill> findBySpecialization(String specialization);
	
	boolean existsBySkillId(long skillId);
	
}
