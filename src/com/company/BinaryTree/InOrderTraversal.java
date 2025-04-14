package com.company.BinaryTree;

public class InOrderTraversal {
    public void inOrderTraversal(TreeNode root){
        if(root == null){
            return;
        }

        inOrderTraversal(root.left);
        System.out.println(root.val);
        inOrderTraversal(root.right);
    }
}
