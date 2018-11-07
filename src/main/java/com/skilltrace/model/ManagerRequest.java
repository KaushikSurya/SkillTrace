package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="ManagerRequest")
public class ManagerRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long managerId;
	
	@NotEmpty(message="Cannot be empty")
	private String managerName;
	
	@NotEmpty(message="Cannot be empty")
	private String courseName;
	
	@NotEmpty(message="Cannot be empty")
	private String description;
	
	
	public ManagerRequest() {

	}


	public ManagerRequest(long managerId, String managerName, String courseName, String description) {

		this.managerId = managerId;
		this.managerName = managerName;
		this.courseName = courseName;
		this.description = description;
	}


	public long getManagerId() {
		return managerId;
	}


	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}


	public String getManagerName() {
		return managerName;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
