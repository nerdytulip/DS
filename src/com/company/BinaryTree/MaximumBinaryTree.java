package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class MaximumBinaryTree {
    /**
     * https://leetcode.com/problems/maximum-binary-tree/description/
     * */

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null){
            return null;
        }

        return build(nums, 0, nums.length-1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if(start>end){
            return null;
        }

        int indexMax = start;
        for(int i = start+1;i<=end;i++){
            if(nums[i] > nums[indexMax]){
                indexMax = i;
            }
        }

        TreeNode root = new TreeNode(nums[indexMax]);

        root.left = build(nums, start, indexMax-1);
        root.right = build(nums,indexMax+1, end);

        return root;
    }
}
