package com.skilltrace.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	private long employeeId;
	
	@NotEmpty(message="Employee name cannot be empty.")
	@Size(min=5, max=20, message="Employee name must be over 5 and under 20 characters.")
	private String employeeName;
	
	private String department;

	private String designation;
	
	@NotEmpty(message="Email cannot be null.")
	@Email(message="Invalid Email Id.")
	private String emailId;
	
	@NotEmpty(message="Mobile Number cannot be null.")
	@Pattern(regexp="\\d{10}", message="Mobile Number can be only of 10 digits.")
	private String mobileNumber;
	
	@NotEmpty(message="Company name cannot be empty.")
	@Size(min=5, max=40, message="Company name must be over 5 and under 40 characters.")
	private String company;

	public Employee() {
	}

	public Employee(long employeeId,
			@NotEmpty(message = "Name cannot be empty.") @Size(min = 5, max = 20, message = "Name must be over 5 and under 20 characters.") String employeeName,
			String department, String designation,
			@NotEmpty(message = "Email cannot be null.") @Email(message = "Invalid Email Id.") String emailId,
			@NotEmpty(message = "Mobile Number cannot be null.") @Pattern(regexp = "\\d{10}", message = "Mobile Number can be only of 10 digits.") String mobileNumber,
			@NotEmpty(message = "Company name cannot be empty.") @Size(min = 5, max = 40, message = "Company name must be over 5 and under 40 characters.") String company) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.department = department;
		this.designation = designation;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.company = company;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
}
