package com.creatingBean.CreatingBean.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.creatingBean.CreatingBean.module.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	
	public List<Employee> findByLastName(String name);
	public List<Employee> findByDepartment(String department);
	
	@Query(value = "select e.* from employee e where e.salary > ?1", nativeQuery = true)
	public List<Employee> findBySalary(Double salary);
}
