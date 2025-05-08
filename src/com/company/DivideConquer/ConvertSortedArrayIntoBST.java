package com.company.DivideConquer;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class ConvertSortedArrayIntoBST {
    public TreeNode helper(int[] nums, int left, int right){
        if(left > right){
            return null;
        }

        int mid = (left+right)/2;
        if((left+right)%2 == 1) ++mid; // to pick right most element if there is no mid

        // preorder
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums,left,mid-1);
        root.right = helper(nums, mid+1,right);

        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length-1);
    }
}
