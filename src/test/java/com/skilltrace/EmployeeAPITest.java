package com.skilltrace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skilltrace.model.Employee;
import com.skilltrace.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeAPITest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webapplicationcontext;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
	}

	@After
	public void tearDown() throws Exception {
		mockMvc=null;
	}

	@Test
	public void testPostNotSupported() throws Exception{
		mockMvc.perform(post("/"))
		.andExpect(status().is4xxClientError()).andDo(print());
		
	}
	
	@Test
	public void testGetAllEmployees() throws Exception{
		assertThat(this.employeeService).isNotNull();
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());
		employees.get(0).setEmployeeId(1);
		employees.get(0).setEmployeeName("Rita");
		employees.get(0).setDepartment("GTS");
		employees.get(0).setDesignation("DESIGNER");
		employees.get(0).setEmailId("rita@sample.com");
		employees.get(0).setMobileNumber("9911772255");
		employees.get(0).setCompany("VDSI, Hyderabad");
		
		when(employeeService.getAllEmployees()).thenReturn(employees);
		
		mockMvc.perform(get("/employees")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testGetEmployeeById() throws Exception {
		assertThat(this.employeeService).isNotNull();
		
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Rita");
		employee.setDepartment("GTS");
		employee.setDesignation("DESIGNER");
		employee.setEmailId("rita@sample.com");
		employee.setMobileNumber("9911772255");
		employee.setCompany("VDSI, Hyderabad");
		
		long employeeId = 1;

		when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

		mockMvc.perform(get("/employees/1")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testAddEmployee() throws IOException, Exception {
		assertThat(this.employeeService).isNotNull();
		
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Rita");
		employee.setDepartment("GTS");
		employee.setDesignation("DESIGNER");
		employee.setEmailId("rita@sample.com");
		employee.setMobileNumber("9911772255");
		employee.setCompany("VDSI, Hyderabad");
		
		when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(employee);
				
		mockMvc.perform(post("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(employee))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testUpdateEmployee() throws IOException, Exception {
		assertThat(this.employeeService).isNotNull();
		
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Rita");
		employee.setDepartment("GTS");
		employee.setDesignation("DESIGNER");
		employee.setEmailId("rita@sample.com");
		employee.setMobileNumber("9911772255");
		employee.setCompany("VDSI, Hyderabad");

		long employeeId = 1;
		

		Employee employeeToUpdate = new Employee();
		employeeToUpdate.setEmployeeId(1);
		employeeToUpdate.setEmployeeName("Sita");
		employeeToUpdate.setDepartment("GTS");
		employeeToUpdate.setDesignation("DESIGNER");
		employeeToUpdate.setEmailId("sita@sample.com");
		employeeToUpdate.setMobileNumber("9911772255");
		employeeToUpdate.setCompany("VDSI, Hyderabad");

		when(employeeService.getEmployeeById(employeeId)).thenReturn(employeeToUpdate);

		when(employeeService.updateEmployee(Mockito.any(Employee.class))).thenReturn(employeeToUpdate);

		mockMvc.perform(put("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(employeeToUpdate))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		assertThat(this.employeeService).isNotNull();
		
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Rita");
		employee.setDepartment("GTS");
		employee.setDesignation("DESIGNER");
		employee.setEmailId("rita@sample.com");
		employee.setMobileNumber("9911772255");
		employee.setCompany("VDSI, Hyderabad");
		
		long employeeId = employee.getEmployeeId();
		
		when(employeeService.deleteById(employeeId)).thenReturn(true);

		mockMvc.perform(delete("/employees/1")).andDo(print());
	}

}
