package com.company.BinaryTree.BST;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class KthSmallestElement {
    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=Zra0mE7M2hE&t=382s
     * */
    private int count = 0;
    private TreeNode result = null;

    public int kthSmallest(TreeNode root, int k) {
        count = 0;         // reset before traversal
        result = null;
        inOrder(root, k);
        return result != null ? result.val : -1;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null || result != null) return;

        inOrder(root.left, k);

        count++;
        if (count == k) {
            result = root;
            return;
        }

        inOrder(root.right, k);
    }
}
