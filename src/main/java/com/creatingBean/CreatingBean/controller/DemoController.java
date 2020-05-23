package com.creatingBean.CreatingBean.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creatingBean.CreatingBean.module.Demo;
import com.creatingBean.CreatingBean.module.DemoChild;
import com.creatingBean.CreatingBean.module.DoubleLinkedList;
import com.creatingBean.CreatingBean.module.Employee;
import com.creatingBean.CreatingBean.module.MyLinkedList;
import com.creatingBean.CreatingBean.module.MyTree;
import com.creatingBean.CreatingBean.repository.A;
import com.creatingBean.CreatingBean.repository.B;
import com.creatingBean.CreatingBean.service.DemoService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

@RestController
@RequestMapping("/hello")
public class DemoController implements A,B{
	
	@Autowired
    Demo demo;
	
	@Autowired
	DemoService service;
	
	@Autowired
	private DoubleLinkedList doubleList;
	
	@GetMapping()
	public List<String> getHello() {
		String inputString[]= {"Mary,Joe" , "U3,U4", "Joe,Mary", "U1,U5"};
	
		List<String> li=Arrays.asList(inputString);
		return service.compareFriends(li);
		
		//return service.getdemo("ABC","ABD");
	}
	
	@GetMapping("map")
	public Object convertListToMap() {
		List<Employee> listEmp=new ArrayList<>();
		listEmp.add(new Employee(1,"naresh@gamil.com","Naresh","IT", 100d));
		listEmp.add(new Employee(2,"mahesh@gamil.com","Mahesh", "HR", 100d));
		listEmp.add(new Employee(3,"ramesh@gamil.com","Ramesh", "Admin", 100d));
		listEmp.add(new Employee(3,"Suresh@gamil.com","Naresh","Admin", 100d));
		
		Map<String, Integer> strMap=new HashMap<>();
		Arrays.asList("Iam going to work as SSE in big MNC company".split("")).stream().forEach(s-> {
			if("AEIOU".contains(s.toUpperCase()))
				strMap.put(s.toUpperCase(), strMap.getOrDefault(s, 0)+1);
		});
		
		Character c='A';
		System.out.println(++c);
		
		System.out.println("=============Stream filer valur===============");
		System.out.println(strMap);
		
		java.util.Comparator<Employee> empByname=(e1,e2)-> e2.getFirstName().compareTo(e1.getFirstName());
		java.util.Comparator<Employee> empByBoth=java.util.Comparator.comparing(Employee::getFirstName).reversed().thenComparing(Employee::getId);
		listEmp.stream().sorted(empByBoth).collect(Collectors.toList());

		
		Map<Integer, Employee> empMap = listEmp.stream().collect(Collectors.toMap(Employee :: getId, employee -> employee
                , (oldValue, newValue) -> oldValue,LinkedHashMap::new));
		
	
		List<String> empNames=listEmp.stream().filter(emp-> emp.getFirstName().startsWith("ar")).map(Employee::getFirstName).collect(Collectors.toList());
		
		Boolean emp= listEmp.stream().filter(emp1-> {
			if(emp1.getId()==3 && emp1.getFirstName().equalsIgnoreCase("suresh")) {
				return true;
			}else {
				return false;
			}
		}).findAny().isPresent();
	
		return empNames;
	}
	
	@GetMapping("sortmap")
	public Object sortMap() {
		Map<String, Integer> occurence=new HashMap<>();
		
		occurence.put("A", 3);
		occurence.put("B", 4);
		occurence.put("C", 7);
		occurence.put("G", 2);
		occurence.put("H", 9);
		
		Map<String, Integer> rsult=occurence.entrySet().stream().sorted(
				Collections.reverseOrder(Map.Entry.comparingByValue())
				).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldvalue,newvalue)-> oldvalue,LinkedHashMap::new));
		
		return rsult;
	}

	@GetMapping("count")
	public Object counting() {
		String para="";
		
		List<String> paraList=new ArrayList<>(Arrays.asList(para.split(" ")));
		
		return paraList;
		
	}
	
	@GetMapping("date")
	public Object convertDate() {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("MMMM dd, YYYY");
		return format.format(date);
	}
	/*
	int uprow=0;
	int leftColumn=0;
	int downRow=0;
	int rightColumn=0;
	
	@GetMapping("array")
	public Object getArray() {
		int intarr[][]=new int[4][4];
		
		intarr[0][0] = 1;
		intarr[0][1] = 2;
		intarr[0][2] = 3;
		intarr[0][3] = 4;

		intarr[1][0] = 5;
		intarr[1][1] = 6;
		intarr[1][2] = 7;
		intarr[1][3] = 8;
		
		intarr[2][0] = 9;
		intarr[2][1] = 10;
		intarr[2][2] = 11;
		intarr[2][3] = 12;
		
		intarr[3][0] = 13;
		intarr[3][1] = 14;
		intarr[3][2] = 15;
		intarr[3][3] = 16;
		
		int row =intarr.length;
		int column=intarr[0].length;
		downRow=row;
		rightColumn=column;
		printArray(intarr, row, 0);
		return intarr;
	}

	private void printArray(int intarr[][], int row, int column) {

		System.out.println("row "+row);
		System.out.println("column "+column);
		for (int i = leftColumn; i < row; i++) {
			System.out.println(intarr[i][column]);
			if(i==row-1) {
				uprow=1;
				printleftArray(intarr, row-1, rightColumn);
			}
		}
	}
	
	
	private void printleftArray(int intarr[][], int row, int column) {
		
		System.out.println("row "+row);
		System.out.println("column "+column);
		for (int i = 0; i < column; i++) {
			System.out.println(intarr[row][i]);
			if(i==column-1) {
				printlowRow(intarr, row, column-1);
			}
		}
	}
	
	private void printlowRow(int intarr[][], int row, int column) {
			
			System.out.println("row "+row);
			System.out.println("column "+column);
			for (int i = row-1; i >=0; i--) {
				System.out.println(intarr[i][column]);
				if(i==0) {
					printrightcolumn(intarr, i, column);
				}
			}
		}
	
	private void printrightcolumn(int intarr[][], int row, int column) {
		
		System.out.println("row "+row);
		System.out.println("column "+column);
		for (int i = column-1; i >=0; i--) {
			System.out.println(intarr[row][i]);
			if(i==uprow) {
				leftColumn+=1;
				uprow+=1;
				rightColumn-=1;
				printuprow(intarr, uprow, leftColumn);
			}
		}
	}
	
private void printuprow(int intarr[][], int row, int column) {
		
		System.out.println("row "+row);
		System.out.println("column "+column);
		for (int i = 0; i <intarr.length; i++) {
			System.out.println(intarr[i][column]);
			if(i==uprow) {
				leftColumn+=1;
				uprow+=1;
				rightColumn-=1;
				printArray(intarr, uprow, leftColumn);
			}
		}
	}*/
	

	@GetMapping("tree")
	public Object treeArray() throws UnknownHostException {
		
		InetAddress localhost = InetAddress.getLocalHost(); 
		
		System.out.println(localhost.getHostAddress().toString().trim());
		MyTree node=new MyTree();
		int n[]= {1,6,10,2,3,8,9};
		int midvalue=n[n.length/2];
		node.add(midvalue);
		for (int i = n.length-1; i >=0; i--) {
			if(midvalue != n[i]) {
				
				node.add(n[i]);
			}
		}
		int hieght=node.heightTree(node.getHead());
		System.out.println("Height of tree is :- "+hieght);
		System.out.println(node.isBST(node.getHead()));
		node.mirrorTree(node.getHead());
		return node.isBST(node.getHead());
	}
	
	char[][] TOKENS= {{'{','}'},{'[',']'},{'(',')'}};
	@GetMapping("para")
	public Object paranthes() {
		String paranth="[](){[()]}";
		Stack<Character> stack=new Stack<>();
		for (Character character : paranth.toCharArray()) {
			if(isOpenTrace(character)) {
				stack.push(character);
			}else {
				if(null != stack.peek()) {
					if(matches(stack.peek(), character)) {
						stack.pop();
					}
				}
				
			}
		}
		return stack.isEmpty();
	}
	
	public boolean isOpenTrace(char c) {
		for (char[] cs : TOKENS) {
			
			if(cs[0]==c) {
				return true;
			}
		}
		return false;
	}
	
	public boolean matches(char opentrace, char closetrace) {
		
		for (char[] cs : TOKENS) {
			if(cs[0]==opentrace)
				return cs[1]==closetrace;
		}
		return false;
	}
	
	@GetMapping("substring/{string}")
	public Object longestLengthSubString(@PathVariable String string) {
		
		int index=0;
		StringBuilder sb=new StringBuilder();
		sb.append(string.charAt(0));
		String resul="";
		for (int i = 1; i < string.length(); i++) {
			
			if( Character.valueOf(string.charAt(i)).equals(Character.valueOf(string.charAt(index))) ) {
				
				if(string.length()-1 > index) {
					index+=1;
					i=index+1;
					if(resul.length() < sb.length()) {
						resul=sb.toString();
					}
					sb=new StringBuilder();
				}
				sb.append(string.charAt(index));
			}
			sb.append(string.charAt(i));
		}
		return resul;
	}
	
	@GetMapping("findDuplicate")
	public Object sortArray() {
		List<Object> list=new ArrayList<>();
		
		int array[]= {1,3,6,3,6,1};
		//int zeroCount=0; 
		
		for (int i = 0; i < array.length; i++) {
			
			if (array[i] > 0) {

				int index = Math.abs(array[i]) - 1;
				if (array[index] < 0) {
					list.add(array[i] < 0 ? Math.abs(array[i]) : array[i]);
				} else {
					array[index] = -array[index];
				}
			} /*
				 * else { zeroCount+=1; } if(zeroCount > 1) { list.add(0); }
				 */
		}
		
		return list +" : "+Arrays.toString(array);
	}
	
	@PostMapping("excelFile")
	public ResponseEntity<InputStreamResource> createExcelFile() throws IOException {
		String jsonString = "{\"infile\": [{\"field1\": 11,\"field2\": 12,\"field3\": 13},{\"field1\": 21,\"field2\": 22,\"field3\": 23},{\"field1\": 31,\"field2\": 32,\"field3\": 33}]}";

		StringBuilder filename=new StringBuilder();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	   	 filename.append("Alert_download_");
	   	// filename.append(timestamp.toString().split(" ")[0]);
	   	 //filename.append("-");
	   	 filename.append(timestamp.toString().replace(".", "_").replace(":", "_"));
	   	 filename.append(".csv");
	   	 String files=filename.toString();
	   	 System.out.println(files);
        JSONObject output;
        String csv=null;
        try {
            output = new JSONObject(jsonString);


            JSONArray docs = output.getJSONArray("infile");
            File file=null;

           file =new File("C:\\Users\\vn0yrph\\Desktop/"+files);
           csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
            
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
	   	 File fileDownload = new File("C:\\Users\\vn0yrph\\Desktop/"+files);
        
        InputStreamResource resource=null;
		try {
			resource = new InputStreamResource(new FileInputStream(fileDownload));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String path = fileDownload.getAbsolutePath();
		File filePath = new File(path);
		filePath.delete();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
        headers.add("Content-Disposition", "attachment; filename=\"employee_1415.pdf\"");
        
        return ResponseEntity.ok().headers(headers).contentLength(fileDownload.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	//return new ResponseEntity(resource, headers, HttpStatus.OK);
	}
	
	@GetMapping("checkwhitespace/{string}")
	public Object checkWhiteSpace(@PathVariable String string) {
		
		System.out.println(string);
		if(string.split(" ").length > 1) {
			if(Character.isDigit(string.charAt(1))) {
				return "contains digit";
			}
		}
		return "contains";
		
	}
	
	@PostMapping("sendMail")
	public Object sendMail() {
		
		Email from = new Email("SamsAlerts@email.wal-mart.com");
	    String subject = "Sending Mail with SendGrid";
	    Email to = new Email("nareshmahman@gmail.com");
	    Content content = new Content("text/plain", "This is a demo mail using sendgrid ");
	    Mail mail = new Mail(from, subject, to, content);
	    
	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      com.sendgrid.Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	    }
		return "sentMail";
	}
	
	@GetMapping("convert")
	public Object convertTimeStampToDate() {
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		Date date = new java.util.Date(timestamp.getTime());
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		String dates=format.format(date);
		return dates;
	}
	
	@GetMapping("list")
	public void addTaverseList() {
		MyLinkedList list=new MyLinkedList();
		
		for (int i = 1; i < 5; i++) {
			list.add(i);
		}
		
		list.traverseNode();
		list.reverselist();
		System.out.println("reverse");
		list.traverseNode();
		
	}
	
	@GetMapping("complexnumber/{number}")
	public Object complexNumber(@PathVariable String number) {
		
		StringJoiner sj=null;
		int testcase=0;
		//String str="3,1 10,10 -10,-1024 2048,2,1 0,0 1";
		String inputArray[]=number.split(",");
		int statIndex=0;
		String result="";
		int i=statIndex;
		for(; i<inputArray.length; i++){
			sj=new StringJoiner(",");
			if(inputArray[i].length()==1){
                testcase=Integer.valueOf(inputArray[i]);
                
                
                for(int j=i+1; j<=i+testcase; j++){
                    System.out.println(inputArray[j]);
                    sj.add(inputArray[j]);
                }
            }if(sj.length()>1) {
            	result+=service.productOf(sj.toString())+"\n";
            	i+=testcase;
            }
            
		}
		
		  //Scanner sc=new Scanner(""); 
		  //while(sc.hasNext()) {
		  //System.out.println(sc.nextLine()+" -"); }
		 
		return result;
	}
	
	@GetMapping("topProduct/{number}")
	public Object topProduct(@PathVariable String number) {
		
		Map<String, Integer> map=null;
		int testcase=0;
		String result="";
		//String str="3,1 10,10 -10,-1024 2048,2,1 0,0 1";
		String inputArray[]=number.split(",");
		for (String product : inputArray) {
			iterateProduct(product);
		}
		/*int statIndex=0;
		String result="";
		int i=statIndex;
		for(; i<inputArray.length; i++){
			map=new HashMap<>();
			System.out.println("index :"+i);
			if(!inputArray[i].isEmpty() && Character.isDigit(inputArray[i].charAt(0))) {
				testcase=Integer.valueOf(inputArray[i]);
				
				for(int j=i+1; j<=i+testcase; j++){
					if(inputArray[j].equalsIgnoreCase("top")) {
						//System.out.println(calculateTop(map));
						result+=calculateTop(map)+"\n";
					}else {
						System.out.println(inputArray[j]);
						addIntoMap(map, inputArray[j]);
					}
                }
				i+=testcase;
			}
		}*/
		return productResult;
	}

	private void calculateTop(Map<String, Integer> map) {

		if (!map.isEmpty()) {

			Map<String, Integer> sortedMap = new LinkedHashMap<>();
			List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
			list.sort(Entry.comparingByValue());

			Map<String, Integer> result = new LinkedHashMap<>();
			for (Entry<String, Integer> entry : list) {
				sortedMap.put(entry.getKey(), entry.getValue());
			}

			/*
			 * map .entrySet() .stream()
			 * .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			 * .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			 */
			sortMap(sortedMap);
		}
	}
	String productResult="";

	private String sortMap(Map<String, Integer> collect) {
		int result=0;
		String resultProduct="";
		PriorityQueue<String> set=new PriorityQueue<>();
		//HashSet<String> set=new HashSet<>();
		for (Entry<String, Integer> entry : collect.entrySet()) {
			
			if(result < entry.getValue()) {
				result=entry.getValue();
				resultProduct=entry.getKey()+" ";
				set.add(entry.getKey());
			}else if(result == entry.getValue()) {
				resultProduct+=entry.getKey()+" ";
				set.add(entry.getKey());
			}
		}
		
		String resulArray[]=resultProduct.split(" ");
		Arrays.sort(resulArray);
		StringBuilder sb=new StringBuilder();
		for (String string : resulArray) {
			sb.append(string);
			sb.append(" ");
		}
		sb.append("\n");
		while(!set.isEmpty()) {
			System.out.print(set.poll()+" ");
		}
		System.out.println();
		System.out.println(collect);
		return productResult+=sb.toString();
		
		
	}

	private void addIntoMap(String string) {

		String key=string.split(" ")[0].trim();
		int value=Integer.valueOf(string.split(" ")[1]);
		if(mapProduct.containsKey(key)) {
			mapProduct.put(key, mapProduct.get(key)+value);
		}else {
			mapProduct.put(key, value);
		}
		//map.put(key, map.getOrDefault(key, value)+value);
	}
	
	Map<String, Integer> mapProduct=null;
	private void iterateProduct(String product) {
		
		if(!product.isEmpty() && Character.isDigit(product.charAt(0))) {
			mapProduct=new HashMap<String, Integer>();
		}else if(product.equalsIgnoreCase("top")){
			calculateTop(mapProduct);
		}else {
			addIntoMap(product);
		}
	}
	
	@GetMapping("drones/{drone}")
	public Object drones(@PathVariable String drone) {
		
		String array[]=drone.split(",");
		int index=0;
		String result="";
		for (String singleDrone : array) {
		
			if(!singleDrone.isEmpty()) {
				
			}
			if(!Character.isDigit(singleDrone.toCharArray()[0])) {
				
				result+= loopdrone(singleDrone, index)+"\n";
			}else {
				index=Integer.valueOf(singleDrone);
			}
		}
		
		return result;
		
	}

	private int loopdrone(String singleDrone, int index) {

		int leftDrone=0;
		int righDrone=0;
		for (int i = 0; i < singleDrone.length(); i++) {
			
			if(singleDrone.charAt(i)=='>') {
				righDrone+=1;
			}else if(singleDrone.charAt(i)=='<') {
				leftDrone+=1;
			}
		}
		
		if(righDrone!=0 && leftDrone!=0 && leftDrone>=righDrone) {
			return checkSequenceAndReplace(singleDrone, ">", "<");
		}else if(righDrone==0) {
			return leftDrone;
		}else if(leftDrone==0) {
			return righDrone;
		}else {
			return checkSequenceAndReplace(singleDrone, "<",">");
		}
	}

	private int checkSequenceAndReplace(String singleDrone, String oldDrone, String newDrone) {

		StringBuilder sb=new StringBuilder(singleDrone);
		int oldResult=0;
		int changes=0;
		for (int i = 0; i < singleDrone.length(); i++) {

			if (String.valueOf(singleDrone.charAt(i)).equalsIgnoreCase(oldDrone)) {
				
				sb.setCharAt(i, newDrone.toCharArray()[0]);
				changes+=1;
			}else {
				if(changes>0) {
					
					int result=getHihestDrone(sb.toString(), newDrone);
					if(result>oldResult) {
						oldResult=result;
					}
					sb=new StringBuilder(singleDrone);
					changes=0;
				}
			}
		}
		
		if(changes>0) {
			
			int result=getHihestDrone(sb.toString(), newDrone);
			if(result>oldResult) {
				oldResult=result;
			}
		}
		System.out.println(oldResult);
		return oldResult;
	}

	private int getHihestDrone(String copyArray, String newDrone) {
		int count=0;
		for (int i=0; i<copyArray.length(); i++) {
			if(String.valueOf(copyArray.charAt(i)).equalsIgnoreCase(newDrone)) {
				count+=1;
			}
		}
		return count;
	}
	
	@GetMapping("{row}/{column}/{length}/{string}")
	public void matrix(@PathVariable String row, @PathVariable String column,@PathVariable String length, @PathVariable String string) {
		
		int tablerow=Integer.valueOf(row);
		int tablecolumn=Integer.valueOf(column);
		if(Integer.valueOf(length)==string.length()) {
			createMatix(tablerow, tablecolumn, string);
		}
	}

	String matrixString="";
	int leftColumn=0;
	int rightColumn=0;
	int uprow=0;
	int dowrow=0;
	int length=0;
	private void createMatix(int tablerow, int tablecolumn, String string) {

		String table[][]=new String[tablerow][tablecolumn];
		matrixString=string;
		leftColumn=0;
		rightColumn=table[0].length-1;
		uprow=0;
		dowrow=table.length-1;
		length=rightColumn*dowrow;
		createMatrix(table);
	}
	

	private void printSprint(String[][] table) {
		// TODO Auto-generated method stub
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j]);
			}
			System.out.println("");
		}
		
	}
	
	Map<String,Integer> example=new HashMap<>();    
	@GetMapping("map/{value}")
	public Object addmap(@PathVariable String value) {
		
		String arr[]=value.split(" ");
		example.put(arr[0], example.getOrDefault(arr[0], 0)+Integer.valueOf(arr[1]));
		
		return reversemap(example);
	}

	private String reversemap(Map<String, Integer> example2) {

		PriorityQueue<String> maxheap=new PriorityQueue<>((a, b) -> example2.get(b) - example2.get(a));
		for (String key : example2.keySet()) {
			maxheap.add(key);
		}
		
		return topProduct(maxheap);
	}

	private String topProduct(PriorityQueue<String> maxheap) {
		int highest=0;
		Set<String> set=new TreeSet<>();
		while (!maxheap.isEmpty()) {
			String key=	maxheap.remove();
			if(highest<=example.get(key)){
				highest=example.get(key);
				set.add(key);
			}else {
				break;
			}
		}
		StringBuilder sb=new StringBuilder();
		for (String string : set) {
			sb.append(string);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public void createMatrix(String arr[][]) {
		
		int count=0;
		String strarr[]=matrixString.split("");
		while(dowrow>=uprow && leftColumn<=rightColumn) {
			
			
			for(int i=leftColumn; i<=rightColumn; i++) {
				if(strarr.length-1<count) {
					count=0;
				}
				arr[uprow][i]=strarr[count++];
			}
			uprow+=1;
			
			for (int i = uprow; i <=dowrow; i++) {
				if(strarr.length-1<count) {
					count=0;
				}
				arr[i][rightColumn]=strarr[count++];
			}
			rightColumn-=1;
			
			for (int i = rightColumn; i >=leftColumn; i--) {
				if(strarr.length-1<count) {
					count=0;
				}
				arr[dowrow][i]=strarr[count++];
			}
			dowrow-=1;
			
			for (int i = dowrow; i >= uprow; i--) {
				if(strarr.length-1<count) {
					count=0;
				}
				arr[i][leftColumn]=strarr[count++];
			}
			leftColumn+=1;
			length-=rightColumn*dowrow;
		}
		
		printSprint(arr);
	}
	
	@GetMapping("doubleLinkedList/{data}")
	public Object doubleLinkedList(@PathVariable String data) {
		doubleList.addNode(Integer.valueOf(data));
		int num[]=new int[10];
		for (int i = 0; i < 10; i++) {
			if(++num[0]==5) {
				System.out.println("found");
			}
		}
		return true;
	}
	
	@GetMapping("sorted/{array}")
	public Object sortsortedArray(@PathVariable String array) {
		
		String inputarray[]=array.split(",");
		List<Integer> output=new ArrayList<>();
		
		for (String string : inputarray) {
			
			if(string.split("").length>1) {
				output.add(Integer.valueOf(string.split("")[0]));
				output.add(Integer.valueOf(string.split("")[1]));
			}else {
				output.add(Integer.valueOf(string));
			}
		}
		Collections.sort(output, Collections.reverseOrder());
		return output.toArray();
	}
	
	@GetMapping("missingnum/{array}")
	public Object getMissingNumber(@PathVariable String array){
		
		String input[]=array.split(",");
		int intarr[]=new int[input.length];
		
		for (int i = 0; i < input.length; i++) {
				intarr[i]=Integer.valueOf(input[i]);
		}
		int total=0;
		for (int i : intarr) {
			total+=i;
		}
		int length=intarr.length+2;
		int length1=intarr.length+1;
		int result=length1 * length/2 ;
		
		Demo d=new DemoChild();
		System.out.println(d.getDemo("hh"));
		
		Demo d1=new DemoChild();
		Map<Demo, String> map=new HashMap<>();
		map.put(new Demo(), "dd");
		map.put(new Demo(), "d2");
		System.out.println(map.size());
		System.out.println(map.get(d1));
		
		return Math.abs(result-total);
	}
	
	@GetMapping("chess")
	public Object chess() {
		String chess[][]=new String[5][5];
		boolean visited[][]=new boolean[5][5];
		int row=chess.length;
		int column=chess[0].length;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(i>0 && i<4 && j==2) {
					chess[i][j]="n";
					visited[i][j]=true;
				}else {
					chess[i][j]="-";
				}
			}
		}
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(chess[i][j]=="n") {
					traverseUpLeft(i, j, visited);
					traverseUpRight(i, j, visited);
					traversedownRight(i, j, visited);
					traverseDownRight(i, j, visited);
				}
			}
		}
		return chess;
	}

	private void traverseUpLeft(int row, int column , boolean[][] visited) {
		int step=2;
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				
			}
		}
	}

	private void traverseUpRight(int row, int column , boolean[][] visited) {
		// TODO Auto-generated method stub
		
	}

	private void traversedownRight(int row, int column , boolean[][] visited) {
		// TODO Auto-generated method stub
		
	}

	private void traverseDownRight(int row, int column , boolean[][] visited) {
		// TODO Auto-generated method stub
		
	}
	
	@GetMapping("combination")
	public Object combinationSum() {
		System.out.println("printing..");
		int candidates[]= {10,1,2,7,6,1,5};
		int target=8;
		List<Integer> list=new ArrayList<>();
		Arrays.sort(candidates);
		findCombinations(candidates, 0, target, new ArrayList<Integer>(), list);
		return list;
	}

	private void findCombinations(int[] candidates, int index, int target, ArrayList<Integer> currentList,
			List<Integer> list) {

		if(target ==0) {
				list.addAll(new ArrayList<>(currentList));
				return;
		}
		
		if(target <0)
			return;
		
		for (int j = index; j < candidates.length; j++) {
			if(j==index || candidates[j] != candidates[j-1]) {
				currentList.add(candidates[j]);
				findCombinations(candidates, j+1, target-candidates[j], currentList, list);
				currentList.remove(currentList.size()-1);
			}
		}
		
	}
	
	@GetMapping("reareenge/{number}")
	public Object getNextNumber(@PathVariable String number) {
		if (number.equals("00:00:00")) {
			return "23:59:59";
		}
		if (Integer.valueOf(number.split(":")[0]) > 23) {
			return "Invalid";
		} else {
			for (String input : number.split(":")) {
				if (Integer.valueOf(input) > 60) {
					return "invalid";
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		String replaceNumber= number.replace(":", "");
		char ar[] = replaceNumber.toCharArray();
		int n = ar.length;
		int i;
		for (i = n - 1; i > 0; i--) {
			if (ar[i] > ar[i - 1]) {
				break;
			}
		}

		int x = ar[i - 1], min = i;

		for (int j = i + 1; j < n; j++) {
			if (ar[j] > x && ar[j] < ar[min]) {
				min = j;
			}
		}

		swap(ar, i - 1, min);

		Arrays.sort(ar, i, n);

		int count = 0;
		for (i = 0; i < n; i++) {
			System.out.print(ar[i]);
			count += 1;
			sb.append(ar[i]);
			if (count == 2) {
				sb.append(":");
				count = 0;
			}
		}
		String highestNumber= sb.toString();
		String output[] = highestNumber.split(":");
		if (Integer.valueOf(output[0]) > 23) {
			return number;
		}else if(Integer.valueOf(output[1]) > 60) {
			return number;
		}else if(Integer.valueOf(output[2]) > 59 && Integer.valueOf(output[2]) > Integer.valueOf(output[1])) {
			return getNextHigest(highestNumber.substring(0, sb.toString().length() - 1), number);
		}else if(Integer.valueOf(output[2]) > 59) {
			return number;
		}

		return highestNumber.substring(0, sb.toString().length() - 1);
	}

	private void swap(char[] ar, int i, int min) {

		char temp = ar[i]; 
        ar[i] = ar[min]; 
        ar[min] = temp;
	}
	
	private String getNextHigest(String number, String lastnumber) {
		StringBuilder sb = new StringBuilder();
		String replaceNumber= number.replace(":", "");
		char ar[] = replaceNumber.toCharArray();
		int n = ar.length;
		int i;
		for (i = n - 1; i > 0; i--) {
			if (ar[i] > ar[i - 1]) {
				break;
			}
		}

		int x = ar[i - 1], min = i;

		for (int j = i + 1; j < n; j++) {
			if (ar[j] > x && ar[j] < ar[min]) {
				min = j;
			}
		}

		swap(ar, i - 1, min);

		Arrays.sort(ar, i, n);

		int count = 0;
		for (i = 0; i < n; i++) {
			System.out.print(ar[i]);
			count += 1;
			sb.append(ar[i]);
			if (count == 2) {
				sb.append(":");
				count = 0;
			}
		}

		
		String output[] = sb.toString().split(":");
		if (Integer.valueOf(output[0]) > 23) {
			return lastnumber;
		}else if(Integer.valueOf(output[1]) > 60) {
			return lastnumber;
		}else if(Integer.valueOf(output[2]) > 60) {
			return lastnumber;
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
	@GetMapping("demo")
	public String getDemo() {
		return service.getdemo("ABC","ABD");
	}
	
	@GetMapping("bitsArray/{array}")
	public Object bitArray(@PathVariable String array) {
		
		int n[]= {1,1,3};	
		/*
		 * for (int i = 0; i < array.length(); i++) {
		 * n[i]=Integer.valueOf(array.charAt(i)); }
		 */
		
		int ones = 0, twos = 0; 
        int common_bit_mask; 
          
        for(int i=0; i<n.length; i++ ) 
        { 
            twos = twos | (ones & n[i]); 
  
            ones = ones ^ n[i]; 
  
            common_bit_mask = ~(ones & twos); 
                          
            /*Remove common bits (the bits that appear third time) from 'ones'*/
            ones &= common_bit_mask; 
                          
            /*Remove common bits (the bits that appear third time) from 'twos'*/
            twos &= common_bit_mask; 
	}
	return ones;
	}
	
	public Object nextSmallestPalindrome(String value) {
		int mid=value.length()/2;
		StringBuffer subValue=new StringBuffer(value.substring(0, mid)).reverse();
		
		for (int i = 0; i < mid; i++) {
			
		}
		return null;
	}
	
	@GetMapping("convert/{decimal}")
	public Object decimalToBinary(@PathVariable Integer decimal) {
		
		String ress="";
		while(decimal > 0) {
			ress+=decimal%2+"";
			
			decimal=decimal/2;
		}
		
		StringBuffer sb=new StringBuffer(ress);
		return sb.reverse().toString();
	}
	
	@GetMapping("reverseNumber/{digit}")
	public Object reverseNumber(@PathVariable Integer digit) {
		Integer result=0;
		while(digit > 0) {
			result=result*10+digit%10;	
			digit=digit/10;
		}
		return result;
	}
	
	@GetMapping("quickSearch")
	public Object heapSearch() {
		
		int array[]= {12,1,3,9,4,23,90,33,56,6,2};
		sort(array, 0, array.length-1);
		
		return Arrays.toString(array);
	}
	
	int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); 
        for (int j=low; j<high; j++) 
        { 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
  
   
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            int midIndex = partition(arr, low, high); 
  
            sort(arr, low, midIndex-1); 
            sort(arr, midIndex+1, high); 
        } 
    }
    
    @GetMapping("{original}/{substring}")
    public Object getLCS(@PathVariable String original, @PathVariable String substring) {
    	
    	return printLCSubStr(original, substring, original.length(), substring.length());
    }
    
	public String printLCSubStr(String X, String Y, int m, int n) {
		int[][] LCSuff = new int[m + 1][n + 1];

		int len = 0;
		int row = 0, col = 0;

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					LCSuff[i][j] = 0;

				else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
					if (len < LCSuff[i][j]) {
						len = LCSuff[i][j];
						row = i;
						col = j;
					}
				} else
					LCSuff[i][j] = 0;
			}
		}

		if (len == 0) {
			return "No Common Substring";
		}

		String resultStr = "";
		while (LCSuff[row][col] != 0) {
			resultStr = X.charAt(row - 1) + resultStr; // or Y[col-1]
			row--;
			col--;
		}
		return resultStr;
	}
	
	@GetMapping("heapsort")
	public Object heapSort() {
		
		int array[]= {30,50,15,20,10};
		for (int i = 0; i < array.length; i++) {
			heapify(array,array.length/2, i);
		}
		for (int i=array.length-1; i>0; i--) 
        { 
            int temp = array[0]; 
            array[0] = array[i]; 
            array[i] = temp; 
  
            heapify(array, i, 0); 
        } 
		return Arrays.toString(array);
	}

	private void heapify(int[] array, int length, int i) {

		int rootIndex = i;
		int leftIndex = 2 * i;
		int rightIndex = 2 * i + 1;

		if (leftIndex < length && array[leftIndex] > array[rootIndex])
			rootIndex = leftIndex;

		if (rightIndex < length && array[rightIndex] > array[rootIndex])
			rootIndex = rightIndex;

		if (rootIndex != i) {
			int swap = array[i];
			array[i] = array[rootIndex];
			array[rootIndex] = swap;

			heapify(array, length, rootIndex);
		}
	}
	
	@GetMapping("numberIsland")
	public Object numberOfIsland() {
		int grid[][]= { { 1, 1, 0, 0, 0 }, 
		                { 0, 1, 0, 0, 1 }, 
		                { 1, 0, 0, 1, 1 }, 
		                { 0, 0, 0, 0, 0 }, 
		                { 1, 0, 1, 0, 1 } };
		
		int numberIslands=0;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				
				if(grid[i][j]==1)
					numberIslands+=dfs(grid, i, j);
					
			}
		}
		
		return numberIslands;
	}

	private int dfs(int[][] grid, int i, int j) {
		if(i<0 || i>=grid.length  || j<0 || j>=grid[i].length || grid[i][j]==0)
			return 0;
		
		grid[i][j]=0;
		dfs(grid, i+1,j);
		dfs(grid, i-1,j);
		dfs(grid, i,j+1);
		dfs(grid, i,j-1);
		return 1;
		
	}
	
	@GetMapping("stringcomb/{string}")
	public Object stringCombination(@PathVariable String string) {
		Set<String> result=new TreeSet<>();
		combinationPermutation(string, "", result);
		return result;
	}

	private void combinationPermutation(String original, String output, Set<String> result) {
		System.out.println(original);
		if(original.length()==0) {
			result.add(output);
		}
		if(original.length()>0) {
		
		combinationPermutation(original.substring(1), output, result);
		
		combinationPermutation(original.substring(1), output + original.charAt(0), result);
		}
	}
	
	@GetMapping("arraycomb")
	public Object arrayCombination() {
		
		int array[] = {1, 2, 3, 4, 5};
		int data[] = new int[3];
		arrayCombination(array, array.length, 3, 0, data , 0);
		return data;
	}

	private void arrayCombination(int[] array, int length, int testcases, int index, int[] data, int currentIndex) {

		if (index == testcases) 
        { 
            for (int j=0; j<testcases; j++) 
                System.out.print(data[j]+" "); 
            System.out.println(""); 
        return; 
        } 
  
        if (currentIndex >= length) 
        return; 
  
        data[index] = array[currentIndex]; 
        arrayCombination(array, length, testcases, index+1, data, currentIndex+1); 
  
        arrayCombination(array, length, testcases, index, data, currentIndex+1);
	} 
	
  
	@GetMapping("distance/{one}/{two}")
	public Object distanceString(@PathVariable String one, @PathVariable String two) {
		
		char ch1[]=one.toLowerCase().toCharArray();
		char ch2[]=two.toLowerCase().toCharArray();
		String twoChar=String.valueOf(ch2);
		
		char ch3[]=new char[ch2.length];
		int index=0;
			for (int i = 0; i < ch2.length; i++) {
				for (int j = 0; j < ch1.length; j++) {
					if(ch2[i]==ch1[j] && ch1[j]!=0 && ch2[i]!=0) {
						ch3[index++]=ch2[i];
						ch2[i]=0;
						ch1[j]=0;
					}
				}
			}
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < ch3.length; i++) {
			if(ch3[i]!=0)
				sb.append(ch3[i]);
		}
		
		return twoChar.length()-sb.toString().length();
	}
	
	@GetMapping("arrayallCombination")
	public Object arraycombination() {
		
		int array[] = {1, 2, 3};
		List<List<Integer>> result=new ArrayList<>();
		
		arrayAllCombination(array, 0, new ArrayList<>(), result);
		return result;
	}

	private void arrayAllCombination(int[] array, int index, ArrayList<Integer> currentList, List<List<Integer>> result) {
		result.add(new ArrayList<>(currentList));
		for (int j = index; j < array.length; j++) {
			currentList.add(array[j]);
			arrayAllCombination(array, j+1, currentList, result);
			currentList.remove(currentList.size()-1);
		}
	}
	
	@GetMapping("hashmapTest")
	public Object hasmhmapTest() {
		Map<Employee,String> map=new HashMap<>();
		map.put(new Employee(1, "naresh", "naidu", "IT", 20000.0), "naresh");
		map.put(new Employee(2, "suresh", "reddy", "IT", 30000.0), "suresh");
		map.put(new Employee(3, "rakesh", "kumar", "IT", 40000.0), "rakesh");
		map.put(new Employee(1, "naresh", "naidu", "IT", 20000.0), "naresh");
		
		System.out.println(map.size());
		System.out.println(map.get(new Employee(3, "rakesh", "kumar", "IT", 40000.0)));
		
		List<String> li=new ArrayList<>();
		li.add("1");
		li.add("2");
		
	Map<String,Integer>	mapcount=Arrays.asList("This is the naresh naidu").parallelStream().flatMap(a-> Arrays.asList(a.split(" ")).stream()).collect(Collectors.toConcurrentMap(c -> {
		if(!c.toString().toLowerCase().isEmpty()) 
			return c;	
		return c;
	}, c->1, Integer::sum));
		System.out.println(mapcount);
		return map;
	}
	
		
}
