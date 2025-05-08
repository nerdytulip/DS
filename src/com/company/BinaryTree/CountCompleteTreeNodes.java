package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class CountCompleteTreeNodes {
    /**
     * https://leetcode.com/problems/count-complete-tree-nodes/description/
     * */

    public int countNodes(TreeNode root) {
        int leftDepth = leftDepth(root);
        int rightDepth = rigthDepth(root);

        if(leftDepth == rightDepth){
            return (int) Math.pow(2,leftDepth) -1;
        }else{
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int rigthDepth(TreeNode root) {
        int dep = 0;
        while(root!=null){
            root = root.right;
            dep++;
        }
        return dep;
    }

    private int leftDepth(TreeNode root) {
        int dep = 0;
        while (root!=null){
            root = root.left;
            dep++;
        }
        return dep;
    }
}
