package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class BalancedBinaryTree {
    /**
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
     *https://www.youtube.com/watch?v=15KwRECEXLU
     * */

    // Top down
    public boolean isBalanced_TD(TreeNode root) {
        if(root == null){
            return true;
        }

        if(Math.abs(height_TD(root.left) - height_TD(root.right)) > 1){
            return false;
        }

        return isBalanced_TD(root.left) && isBalanced_TD(root.right);
    }

    private int height_TD(TreeNode node){
        if(node == null){
            return 0;
        }else{
            return 1 + Math.max(height_TD(node.left), height_TD(node.right));
        }
    }

    // Binary Tree
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }

        return height(root)!=-1;
    }

    public int height(TreeNode node){
        if(node == null){
            return 0;
        }

        int left = height(node.left);
        int right = height(node.right);
        int balanceFactor = Math.abs(left-right);

        if(balanceFactor>1 || left == -1 || right == -1){
            return -1;
        }else{
            return 1 + Math.max(left,right);
        }
    }

}
