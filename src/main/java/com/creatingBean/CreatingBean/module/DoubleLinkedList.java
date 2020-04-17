package com.creatingBean.CreatingBean.module;

import org.springframework.stereotype.Component;

@Component
public class DoubleLinkedList {
	
	DoubleNode head;
	
	
	public void addNode(int data) {
		DoubleNode node=new DoubleNode(data);
		
		node.next=head;
		node.prev=null;
		
		if(head!=null)
			head.prev=node;
		
		head=node;
		
	}

}
