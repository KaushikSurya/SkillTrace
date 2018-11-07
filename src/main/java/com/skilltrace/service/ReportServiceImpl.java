package com.skilltrace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.model.EmployeeReport;
import com.skilltrace.dao.ReportRepo;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepo reportRepo;

	@Autowired
	private SkillService skillService;

	@Override
	public List<EmployeeReport> getAllReports() {
		return reportRepo.findAll();
	}

	@Override
	public EmployeeReport getReportById(long skillId) {
		Optional<EmployeeReport> opt = reportRepo.findById(skillId);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public EmployeeReport addReport(long employeeId, String employeeName) {
		EmployeeReport report = new EmployeeReport();
		report.setEmployeeId(employeeId);
		report.setEmployeeName(employeeName);
		double finalScore = skillService.calculateFinalScore(employeeId);
		report.setFinalScore(finalScore);
		reportRepo.save(report);
		return report;
	}

	@Override
	public void addReports(long empId, String empName,  double finalScore) {
		EmployeeReport report = new EmployeeReport();
		//System.out.println("Employee #" + i + " : " + emp.toString());
		report.setEmployeeId(empId);
		System.out.println("Id : " + empId);
		report.setEmployeeName(empName);
		System.out.println("Name : " + empName);
		// double finalScore1 = 0.0;
		report.setFinalScore(finalScore);
		reportRepo.save(report);
	}

}
