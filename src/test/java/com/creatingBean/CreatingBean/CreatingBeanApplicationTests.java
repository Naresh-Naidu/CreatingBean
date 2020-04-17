package com.creatingBean.CreatingBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.creatingBean.CreatingBean.controller.DemoController;
import com.creatingBean.CreatingBean.service.DemoService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CreatingBeanApplicationTests {

	@Mock
	private DemoService service;
	
	@InjectMocks
	private DemoController controller;
	
	@Test
	public void randomTest() {
		
		String result="hai";
		when(service.getdemo(Mockito.anyString(), Mockito.anyString())).thenReturn(result);
		
		assertEquals(controller.getDemo(), result);
	}
	
}
