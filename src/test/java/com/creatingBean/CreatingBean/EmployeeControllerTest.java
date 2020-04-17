package com.creatingBean.CreatingBean;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.creatingBean.CreatingBean.controller.EmployeeController;
import com.creatingBean.CreatingBean.module.Employee;
import com.creatingBean.CreatingBean.service.EmployeeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	
	@Test
	public void createEmployee() {
		Employee emp=new Employee(1, "Naresh", "Naidu", "IT", 12000d);
		when(employeeService.createEmployee(emp)).thenReturn("created successfully");
		assertNotNull(employeeController.createEmplyee(emp));
	}
	
	@Test
	public void getAllEmployee() {
		//Employee emp=new Employee(1, "Naresh", "Naidu", "IT");
		List<Employee> liEmp=new ArrayList<>();
		when(employeeService.getAllEmployee()).thenReturn(liEmp);
		assertNotNull(employeeController.getAllEmployee());
	}
}
