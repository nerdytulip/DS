package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

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
