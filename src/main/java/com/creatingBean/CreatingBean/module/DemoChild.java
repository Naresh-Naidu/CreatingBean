package com.creatingBean.CreatingBean.module;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DemoChild extends Demo{

	public String getDemo(String message) {
		System.out.println("child demo method");
		return "ggg";
	}
	
	public String getdemochild() {
		return "child";
	}
}
