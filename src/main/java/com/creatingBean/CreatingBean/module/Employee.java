package com.creatingBean.CreatingBean.module;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String lastName;
	private String department;
	private Double salary;
	
	public Employee() {
		
	}
}
