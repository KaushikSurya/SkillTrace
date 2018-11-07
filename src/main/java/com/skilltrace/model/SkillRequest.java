package com.skilltrace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class SkillRequest {

	@Id
	private long requestId;
	
	@NotNull
	private long employeeId;
	
	@NotEmpty(message="Employee name cannot be empty.")
	private String employeeName;
	
	@NotNull
	@Column(name="skill_id")
	private long skillRequestedId;
	
	@NotEmpty(message="Skill name cannot be empty.")
	@Column(name="skill_name")
	private String skillRequestedName;
	
	private String status;

	public SkillRequest() {
	}

	public SkillRequest(long requestId, @NotNull long employeeId,
			@NotEmpty(message = "Employee name cannot be empty.") String employeeName,
			@NotNull long skillRequestedId,
			@NotEmpty(message = "Skill name cannot be empty.") String skillRequestedName,
			String status) {
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.skillRequestedId = skillRequestedId;
		this.skillRequestedName = skillRequestedName;
		this.status = status;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
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

	public long getSkillRequestedId() {
		return skillRequestedId;
	}

	public void setSkillRequestedId(long skillRequestedId) {
		this.skillRequestedId = skillRequestedId;
	}

	public String getSkillRequestedName() {
		return skillRequestedName;
	}

	public void setSkillRequestedName(String skillRequestedName) {
		this.skillRequestedName = skillRequestedName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
