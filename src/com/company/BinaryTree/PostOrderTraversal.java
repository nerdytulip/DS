package com.company.BinaryTree;

public class PostOrderTraversal {
    public void postOrderTraversal(TreeNode root){
        if(root == null){
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.val);
    }
}
