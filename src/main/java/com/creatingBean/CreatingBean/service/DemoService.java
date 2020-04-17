package com.creatingBean.CreatingBean.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatingBean.CreatingBean.module.Demo;
import com.creatingBean.CreatingBean.repository.EmployeeRepository;

@Service
public class DemoService {
	
	@Autowired
	Demo demo;
	
	//@Autowired
	//EmployeeRepository repository;
	
	public String getdemo(String input1, String input2) {
		
		StringBuilder output1=new StringBuilder();
		StringBuilder output2=new StringBuilder();
		for (int i = 0; i < input1.length(); i++) {
			
			if(!input2.contains(Character.toString(input1.charAt(i)))) {
				output1.append(input1.charAt(i));
			}
		}
		
		for (int i = 0; i < input2.length(); i++) {
					
				if(!input1.contains(Character.toString(input2.charAt(i)))) {
					output2.append(input2.charAt(i));
				}
			}
		
		return output1.toString()+" -- "+output2.toString();
	}
	
	public List<String> compareFriends(List<String> frndList){
		
		List<String> result=new ArrayList<String>();
		StringBuilder sb=null;
		for (String frnds : frndList) {
			
			if(!result.contains(frnds)) {
				sb=new StringBuilder();
				String arr[]=frnds.split(",");
				if(arr.length==2) {
					sb.append(arr[1]);
					sb.append(",");
					sb.append(arr[0]);
				}
				if(!result.contains(sb.toString())) {
					result.add(frnds);
				}
			}
		}
		Collections.sort(result);
		return result;
	}
	int aRealnumber=0;
	int aImagenumber=0;
	public Object productOf(String complexNumber) {
		aRealnumber=0;
		aImagenumber=0;
		int bRealnumber=0;
		int bImagenumber=0;
		String number[]=complexNumber.split(",");
		
		if(number.length>2) {
			aRealnumber=Integer.valueOf(number[0].split(" ")[0]);
			aImagenumber=Integer.valueOf(number[0].split(" ")[1]);
			
		}
		for (int i = 1; i < number.length; i++) {

			bRealnumber = Integer.valueOf(number[i].split(" ")[0]);
			bImagenumber = Integer.valueOf(number[i].split(" ")[1]);

			multiplayComplexnumber(aRealnumber, bRealnumber, aImagenumber, bImagenumber);

		}
		
		/*
		 * if(number.length>2) {
		 * aRealnumber=Integer.valueOf(number[0].split("i")[0].split(" ")[0]);
		 * aImagenumber=Integer.valueOf(number[0].split("i")[0].split(" ")[1]);
		 * 
		 * }
		 * 
		 * for (int i = 1; i < number.length; i++) {
		 * 
		 * bRealnumber=Integer.valueOf(number[i].split("i")[0].split(" ")[0]);
		 * bImagenumber=Integer.valueOf(number[i].split("i")[0].split(" ")[1]);
		 * 
		 * multiplayComplexnumber(aRealnumber, bRealnumber, aImagenumber, bImagenumber);
		 * 
		 * }
		 */
		return 
				aRealnumber+" "+
				aImagenumber+"";
		
	}
	
	public void multiplayComplexnumber(int arealnumber, int brealnumber, int aimagenumber, int bimgaenumber) {
		
		aRealnumber=(arealnumber*brealnumber - aimagenumber*bimgaenumber);
		aImagenumber=(arealnumber*bimgaenumber + aimagenumber*brealnumber);
		
	}

}
