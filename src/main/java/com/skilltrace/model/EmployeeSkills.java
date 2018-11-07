package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class EmployeeSkills {

	@Id
	private long employeeSkillsId;
	
	@NotNull
	private long employeeId;
	
	@NotEmpty(message="Employee name cannot be empty.")
	private String employeeName;
	
	@NotNull
	private long skillId;
	
	@NotEmpty(message="Skill name cannot be empty.")
	private String skillName;
	
	@NotNull
	private int level;

	public EmployeeSkills() {
	}

	public EmployeeSkills(long employeeSkillsId, @NotNull long employeeId,
			@NotEmpty(message = "Employee name cannot be empty.") String employeeName,
			@NotNull long skillId,
			@NotEmpty(message = "Skill name cannot be empty.") String skillName,
			@NotNull int level) {
		this.employeeSkillsId = employeeSkillsId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.skillId = skillId;
		this.skillName = skillName;
		this.level = level;
	}

	public long getEmployeeSkillsId() {
		return employeeSkillsId;
	}

	public void setEmployeeSkillsId(long employeeSkillsId) {
		this.employeeSkillsId = employeeSkillsId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
		
}
