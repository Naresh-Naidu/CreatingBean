package com.creatingBean.CreatingBean.module;

public class MyLinkedList {

	
	ListNode head;
	int data;
	int size;
	
	public void add(int data) {
		ListNode node=new ListNode(data);
		
		node.next=head;
		head=node;
		size+=1;
	}
	
	public void traverseNode() {
		ListNode currentNode=head;
		while(currentNode!=null) {
			System.out.println(currentNode.data);
			currentNode=currentNode.next;
		}
	}
	
	public void reverselist() {
		ListNode currentNod = head;
		
		ListNode next=null;
		ListNode prev=null;
		while(currentNod != null) {
			next=currentNod.next;
			currentNod.next=prev;
			prev=currentNod;
			currentNod=next;
		}
		head=prev;
	}
	
	
	public ListNode reverseUtil(ListNode current, ListNode prev) {
		
		 if (current.next == null) { 
	            head = current; 
	  
	            /* Update next to prev node */
	            current.next = prev; 
	            return null; 
	        } 
	  
	        /* Save curr->next node for recursive call */
	        ListNode next1 = current.next; 
	  
	        /* and update next ..*/
	        current.next = prev; 
	  
	        reverseUtil(next1, current); 
	        return head; 
	}
	
	
}
