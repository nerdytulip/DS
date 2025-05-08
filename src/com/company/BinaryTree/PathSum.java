package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class PathSum {
    /**
     * https://leetcode.com/problems/path-sum/description/
     * */

    //level order traversal
    public boolean hasPathSum_UsingArrayDeque(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Integer> sumQueue = new ArrayDeque<>();

        nodeQueue.offer(root);
        sumQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode currentNode = nodeQueue.poll();
            int currentSum = sumQueue.poll();

            // Check if it's a leaf node and if the sum equals targetSum
            if (currentNode.left == null && currentNode.right == null && currentSum == targetSum) {
                return true;
            }

            // Enqueue the left child
            if (currentNode.left != null) {
                nodeQueue.offer(currentNode.left);
                sumQueue.offer(currentSum + currentNode.left.val);
            }

            // Enqueue the right child
            if (currentNode.right != null) {
                nodeQueue.offer(currentNode.right);
                sumQueue.offer(currentSum + currentNode.right.val);
            }
        }

        return false;
    }

    // using recursive stack - DFS
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        if(root.val==targetSum && root.left==null && root.right==null){
            return true;
        }
        boolean a=hasPathSum(root.left, targetSum-root.val);
        boolean b=hasPathSum(root.right, targetSum-root.val);
        return a||b;
    }
}
