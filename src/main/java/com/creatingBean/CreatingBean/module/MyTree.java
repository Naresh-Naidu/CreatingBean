package com.creatingBean.CreatingBean.module;

public class MyTree {

	
	TreeNode head;
	
	
	
	public TreeNode getHead() {
		return head;
	}


	public void add(int value) {
		if(head==null) {
			TreeNode node=new TreeNode(value);
			head=node;
		}else {
			head.insertNode(value);
		}
	}
	
	public boolean isBST(TreeNode root) {
	
		boolean result=true;
	
		if(root==null)
			return result;
		
		if(root.right !=null && root.right.data < root.data)
			return result=false;
		
		if(root.left !=null && root.left.data > root.data)
			return result=false;
		
		isBST(root.left);
		isBST(root.right);
		return result;
	}

	public int heightTree(TreeNode root) {

		if(root==null) {
			return 0;
		}else {
			
			int left=heightTree(root.left);
			int right=heightTree(root.right);
			
			if(left > right) {
				return 1+left;
			}else {
				return 1+right;
			}
		}
	}


	public TreeNode mirrorTree(TreeNode root) {

		if(root != null) {
			TreeNode temp= root.left;
			root.left=root.right;
			root.right=temp;
			
			mirrorTree(root.left);
			mirrorTree(root.right);
			
		}
		
		return root;
	}
	
	
}
