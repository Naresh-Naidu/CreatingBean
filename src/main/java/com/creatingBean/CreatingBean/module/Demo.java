package com.creatingBean.CreatingBean.module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Demo {

	
	public Demo() {
		System.out.println("Initiate parent demo class");
	}
	
	public String getDemo(String message) throws ArithmeticException  {
		System.out.println("parent demo method");
		return "ggg";
	}
}
