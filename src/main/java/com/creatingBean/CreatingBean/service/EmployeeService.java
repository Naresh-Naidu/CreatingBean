package com.creatingBean.CreatingBean.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creatingBean.CreatingBean.module.Employee;
import com.creatingBean.CreatingBean.repository.EmployeeRepository;

@Component
public class EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public String createEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "created sucessfully";
	}

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeRepository.findAll();
		//return null;
	}
	
	public List<Employee> getEmployeeByName(String name){
		return employeeRepository.findByLastName(name);
		//return null;
	}
	
	public List<Employee> getEmployeeByDepartment(String department){
		return employeeRepository.findByDepartment(department);
	}
	
	public List<Employee> getEmployeeBySalary(Double salary){
		
		return employeeRepository.findBySalary(salary);
	}
	
}
