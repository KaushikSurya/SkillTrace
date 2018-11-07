package com.skilltrace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skilltrace.model.Employee;
import com.skilltrace.service.EmployeeService;
import com.skilltrace.service.ReportService;
import com.skilltrace.service.SkillService;

@SpringBootApplication
public class SkillTraceApplication implements CommandLineRunner{

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private SkillService skillService;
		
	public static void main(String[] args) {
		
		SpringApplication.run(SkillTraceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = empService.getAllEmployees();
		for(int i=0; i<employees.size(); i++) {
			long empId = employees.get(i).getEmployeeId();
			String empName = employees.get(i).getEmployeeName();
			double finalScore = skillService.calculateFinalScore(empId);
			reportService.addReports(empId,empName,finalScore);
		}
	}
}
