package com.creatingBean.CreatingBean;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.creatingBean.CreatingBean.controller.DemoController;
import com.creatingBean.CreatingBean.controller.EmployeeController;
import com.creatingBean.CreatingBean.module.Employee;
import com.creatingBean.CreatingBean.service.DemoService;
import com.creatingBean.CreatingBean.service.EmployeeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CreatingBeanApplicationTests {

	@Mock
	private DemoService service;
	
	@Mock
	private EmployeeService empservice;
	
	@InjectMocks
	private DemoController controller;
	
	@InjectMocks
	private EmployeeController empcontroller;
	
	@Test
	public void randomTest() {
		
		String result="hai";
		when(service.getdemo(Mockito.anyString(), Mockito.anyString())).thenReturn(result);
		
		assertEquals(controller.getDemo(), result);
	}
	
	@Test
	public void crateEmployee() {
		Employee emp=new Employee();
		emp.setId(1);
		emp.setDepartment("IT");
		emp.setFirstName("Naresh");
		emp.setLastName("Naidu");
		emp.setSalary(1200000d);
		
		when(empservice.createEmployee(emp)).thenReturn("");
		assertNotNull(empcontroller.createEmplyee(emp));
	}
	
}
