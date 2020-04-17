package com.creatingBean.CreatingBean.module;

public class TreeNode {

	public TreeNode left, right;
	int data;
	
	public TreeNode(int data){
		this.data=data;
		left=null;
		right=null;
	}
	
	public TreeNode() {
		
	}
	public void insertNode(int value) {
		if(value < data) {
			if(left==null) {
				left=new TreeNode(value);
			}else {
				left.insertNode(value);
			}
		}else {

			if(right==null) {
				right=new TreeNode(value);
			}else {
				right.insertNode(value);
			}
		
		}
	}
	
	public void preOrder(TreeNode node) {
		if(node !=null) {
			
			System.out.println(node.data);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	
}
