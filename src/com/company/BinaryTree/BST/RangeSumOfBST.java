package com.company.BinaryTree.BST;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }

        int sum = 0;

        // explore left tree only if the value of root is greater than lower limit
        if(root.val>low){
            sum+=rangeSumBST(root.left,low,high);
        }

        // if the value is in range , add it to sum
        if(root.val>=low && root.val<=high){
            sum+=root.val;
        }

        // explore right tree only if root.val is less than high
        if(root.val<high){
            sum += rangeSumBST(root.right,low,high);
        }

        return sum;
    }
}
