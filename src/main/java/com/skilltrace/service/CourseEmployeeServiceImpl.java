package com.skilltrace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.dao.CourseEmployeeRepo;
import com.skilltrace.model.CourseEmployee;

@Service
public class CourseEmployeeServiceImpl implements CourseEmployeeService {

	@Autowired
	private CourseEmployeeRepo courseEmpRepo;
	
	@Override
	public CourseEmployee addCourseEmployee(CourseEmployee courseEmp) {
		return courseEmpRepo.save(courseEmp);
	}

}
