package com.company.BinaryTree;

public class PreOrderTraversal {

    public void preOrderTraversal(TreeNode root){
        if(root == null){
            return;
        }

        System.out.println(root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}
