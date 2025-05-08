package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AverageOfBinaryTreeLevel {
    /**
     * https://leetcode.com/problems/average-of-levels-in-binary-tree/
     * */
    public List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode> treeNodeQueue = new ArrayDeque<>();
        // Create a unique marker node; use a new instance that you'll check by reference.
        TreeNode marker = new TreeNode(0); // the value here doesn't matter

        treeNodeQueue.add(root);
        treeNodeQueue.add(marker);

        double sum = 0;
        int nodes = 0;

        List<Double> avgList = new ArrayList<>();

        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.poll();

            // Check if the marker is reached; use reference comparison.
            if (node == marker) {
                avgList.add(sum / nodes);
                sum = 0;
                nodes = 0;
                // If there are more nodes, add the marker to denote end of next level
                if (!treeNodeQueue.isEmpty()) {
                    treeNodeQueue.offer(marker);
                }
            } else {
                sum += node.val;
                nodes++;
                if (node.left != null) treeNodeQueue.offer(node.left);
                if (node.right != null) treeNodeQueue.offer(node.right);
            }
        }

        return avgList;
    }
}
