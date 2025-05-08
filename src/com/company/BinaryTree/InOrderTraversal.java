package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

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
