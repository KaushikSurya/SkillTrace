package com.skilltrace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltrace.model.EmployeeReport;

@Repository
public interface ReportRepo extends JpaRepository<EmployeeReport, Long> {
	
}
