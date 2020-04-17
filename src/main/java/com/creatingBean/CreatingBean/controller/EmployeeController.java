package com.creatingBean.CreatingBean.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creatingBean.CreatingBean.module.Employee;
import com.creatingBean.CreatingBean.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/employee")
//@SuppressWarnings("unchecked")
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping
//	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> createEmplyee(@RequestBody Employee employee){
		log.info("request for creating employee");
		return new ResponseEntity<String>(employeeService.createEmployee(employee), HttpStatus.CREATED);
	}
	
	@GetMapping
	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		log.info("request for get all employee");
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("{name}")
	public ResponseEntity<List<Employee>> getEmployee(@PathVariable String name){
		log.info("request for get "+name+" employee");
		return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByName(name), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("department/{departmentName}")
	public ResponseEntity<List<Employee>> getBydepartment(@PathVariable String departmentName){
		log.info("request for get "+departmentName+" employee");
		return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByDepartment(departmentName), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("salary/{sal}")
	public ResponseEntity<List<Employee>> getBySalary(@PathVariable String sal){
		//return new ResponseEntity<List<Employee>>(employeeService.getEmployeeBySalary(Double.valueOf(sal)), HttpStatus.ACCEPTED);
		throw new NullPointerException();
	}
}
