package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="CourseEmployee")
public class CourseEmployee {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long employeeId;
	
	
	@NotEmpty(message="Name can not be empty")
	private String employeeName;
	
	@NotNull(message="Cannot be empty")
	private long skillId;
	
	@NotEmpty(message="Name can not be empty")
	private String skillName;

	@NotEmpty(message="Cannot be empty")
	private String status;

	public CourseEmployee() {
		super();
	}

	public CourseEmployee(long employeeId,String employeeName,long skillId,String skillName,String status) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.skillId = skillId;
		this.skillName = skillName;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
