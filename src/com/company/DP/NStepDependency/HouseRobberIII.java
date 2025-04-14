package com.company.DP.NStepDependency;

import com.company.BinaryTree.TreeNode;

public class HouseRobberIII {

    /**
     * https://leetcode.com/problems/house-robber-iii/description/
     * */

    public int rob(TreeNode root) {
        int[] options = travel(root);
        return Math.max(options[0],options[1]);
    }

    private int[] travel(TreeNode root){
        if(root == null){
            return new int[2];
        }

        int[] left_node_choices = travel(root.left);
        int[] right_node_choices = travel(root.right);
        int[] options = new int[2];

        // if looted : i.e include
        options[0] = root.val + left_node_choices[1] + right_node_choices[1];

        // if not looted : i.e exclude current
        options[1] = Math.max(left_node_choices[0],left_node_choices[1]) + Math.max(right_node_choices[0],right_node_choices[1]);

        return options;
    }
}
