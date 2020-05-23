package com.creatingBean.CreatingBean.module;

import org.springframework.stereotype.Component;

@Component
public class Demo {

	
	public Demo() {
		System.out.println("Initiate parent demo class");
	}
	
	public String getDemo(Object message) throws ArithmeticException  {
		System.out.println("parent demo method");
		return "ggg";
	}
}
