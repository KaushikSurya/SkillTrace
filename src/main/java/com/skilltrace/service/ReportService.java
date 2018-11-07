 package com.skilltrace.service;

import java.util.List;

import com.skilltrace.model.EmployeeReport;

public interface ReportService {
	
	public List<EmployeeReport> getAllReports();
	public EmployeeReport getReportById(long skillId);
	public EmployeeReport addReport(long employeeId, String employeeName);
	public void addReports(long empId, String empName, double finalScore);
	
}
