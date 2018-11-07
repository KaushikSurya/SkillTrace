package com.skilltrace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
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
import com.skilltrace.model.EmployeeSkills;
import com.skilltrace.service.EmployeeSkillsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeSkillsAPITest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webapplicationcontext;
	
	@MockBean
	private EmployeeSkillsService employeeSkillsService;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webapplicationcontext).build();
	}

	@After
	public void tearDown() throws Exception {
		mockMvc=null;
	}
	
	@Test
	public void testGetAllEmployeeSkillss() throws Exception {
		assertThat(this.employeeSkillsService).isNotNull();
		
		List<EmployeeSkills> employeeSkills = new ArrayList<EmployeeSkills>();
		employeeSkills.add(new EmployeeSkills());
		employeeSkills.get(0).setEmployeeId(1);
		employeeSkills.get(0).setEmployeeName("Rita");
		employeeSkills.get(0).setEmployeeSkillsId(1);
		employeeSkills.get(0).setSkillId(1);
		employeeSkills.get(0).setSkillName("Java");
		employeeSkills.get(0).setLevel(8);
		
		when(employeeSkillsService.getAllEmployeeSkills()).thenReturn(employeeSkills);
		
		mockMvc.perform(get("/employeeSkills")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testGetEmployeeSkillsById() throws Exception {
		assertThat(this.employeeSkillsService).isNotNull();
		
		EmployeeSkills employeeSkill = new EmployeeSkills();
		employeeSkill.setEmployeeId(1);
		employeeSkill.setEmployeeName("Rita");
		employeeSkill.setEmployeeSkillsId(1);
		employeeSkill.setSkillId(1);
		employeeSkill.setSkillName("Java");
		employeeSkill.setLevel(8);
		
		when(employeeSkillsService.getEmployeeSkillsById(employeeSkill.getEmployeeSkillsId())).thenReturn(employeeSkill);
		
		mockMvc.perform(get("/employeeSkills/1")).andExpect(status().isOk()).andDo(print());
		}

	@Test
	public void testAddEmployeeSkills() throws IOException, Exception {
		assertThat(this.employeeSkillsService).isNotNull();
		
		EmployeeSkills employeeSkill = new EmployeeSkills();
		employeeSkill.setEmployeeId(1);
		employeeSkill.setEmployeeName("Rita");
		employeeSkill.setEmployeeSkillsId(1);
		employeeSkill.setSkillId(1);
		employeeSkill.setSkillName("Java");
		employeeSkill.setLevel(8);
		
		when(employeeSkillsService.addEmployeeSkills(Mockito.any(EmployeeSkills.class))).thenReturn(employeeSkill);
				
		mockMvc.perform(post("/employeeSkills").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(employeeSkill))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testUpdateEmployeeSkills() throws IOException, Exception {
		assertThat(this.employeeSkillsService).isNotNull();
		
		EmployeeSkills employeeSkill = new EmployeeSkills();
		employeeSkill.setEmployeeId(1);
		employeeSkill.setEmployeeName("Rita");
		employeeSkill.setEmployeeSkillsId(1);
		employeeSkill.setSkillId(1);
		employeeSkill.setSkillName("Java");
		employeeSkill.setLevel(8);
		
		EmployeeSkills employeeSkillToUpdate = new EmployeeSkills();
		employeeSkillToUpdate.setEmployeeId(1);
		employeeSkillToUpdate.setEmployeeName("Rita");
		employeeSkillToUpdate.setEmployeeSkillsId(1);
		employeeSkillToUpdate.setSkillId(1);
		employeeSkillToUpdate.setSkillName("Adv Java");
		employeeSkillToUpdate.setLevel(8);
		
		when(employeeSkillsService.getEmployeeSkillsById(1)).thenReturn(employeeSkillToUpdate);

		when(employeeSkillsService.updateEmployeeSkills(Mockito.any(EmployeeSkills.class))).thenReturn(employeeSkillToUpdate);

		mockMvc.perform(put("/employeeSkills").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(employeeSkillToUpdate))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
		}

	@Test
	public void testDeleteEmployeeSkills() throws Exception {
		assertThat(this.employeeSkillsService).isNotNull();
		
		EmployeeSkills employeeSkill = new EmployeeSkills();
		employeeSkill.setEmployeeId(1);
		employeeSkill.setEmployeeName("Rita");
		employeeSkill.setEmployeeSkillsId(1);
		employeeSkill.setSkillId(1);
		employeeSkill.setSkillName("Java");
		employeeSkill.setLevel(8);
		
		when(employeeSkillsService.deleteEmployeeSkillsById(employeeSkill.getEmployeeSkillsId())).thenReturn(true);

		mockMvc.perform(delete("/employeeSkills/1")).andDo(print());
	}

}
