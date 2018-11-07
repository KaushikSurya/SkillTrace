package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
	
@Entity
@Table
public class EmployeeReport {
	
	@Id
	private long employeeId;
	
	@NotEmpty(message="Cannot be empty")
	private String employeeName;
	
	@NotNull(message="Cannot be null")
	private double finalScore;
	
	
	public EmployeeReport() {
	}
	

	public EmployeeReport(long employeeId, @NotEmpty(message = "Cannot be empty") String employeeName,
			@NotNull(message = "Cannot be null") double finalScore) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.finalScore = finalScore;
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

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}
	
	
}
