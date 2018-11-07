package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="skills")
public class Skill {
	
	@Id
	private long skillId;
	
	@NotEmpty(message="Skill name cannot be empty.")
	private String skillName;
	
	@NotNull
	private double score;
	
	private String specialization;

	public Skill() {
	}

	public Skill(long skillId, @NotEmpty(message = "Skill name cannot be empty.") String skillName,
			@NotNull double score, String specialization) {
		this.skillId = skillId;
		this.skillName = skillName;
		this.score = score;
		this.specialization = specialization;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
		
}
