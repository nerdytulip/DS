package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class SumRootToLeafNumbers {
    private int solve(TreeNode root, int curr){
        if(root == null){
            return 0;
        }

        curr = curr*10 + root.val;

        // leaf node
        if(root.left == null && root.right == null){
            return curr;
        }

        int l = solve(root.left,curr);
        int r = solve(root.right,curr);

        return l+r;
    }

    public int sumNumbers(TreeNode root) {
        return solve(root, 0);
    }
}
