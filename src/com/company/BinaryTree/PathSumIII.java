package com.company.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class PathSumIII {

    public int pathSum_IncludeExclude(TreeNode root, int targetSum) {
        return pathSum(root, (long) targetSum);
    }

    private int pathSum(TreeNode node, long targetSum) {
        if (node == null) {
            return 0;
        }

        // "Include" the current node: Count all paths starting from this node
        int pathsFromNode = countPathsFrom(node, targetSum);

        // "Exclude" the current node: Count paths in the left and right subtrees
        int pathsFromLeft = pathSum(node.left, targetSum);
        int pathsFromRight = pathSum(node.right, targetSum);

        return pathsFromNode + pathsFromLeft + pathsFromRight;
    }

    // This helper counts paths that start at 'node' and have a sum equal to targetSum.
    private int countPathsFrom(TreeNode node, long targetSum) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        // Check if current node completes the path with the target sum.
        if ((long) node.val == targetSum) {
            count++;
        }

        // Continue including child nodes in the path.
        count += countPathsFrom(node.left, targetSum - node.val);
        count += countPathsFrom(node.right, targetSum - node.val);

        return count;
    }

    // Using prefix sum
    public int pathSum(TreeNode root, int sum) {
        int count = 0;
        HashMap<Long, Integer> h = new HashMap<>();
        preorder(root, 0L,sum,h,count);
        return count;
    }

    public void preorder(TreeNode node, long currSum,int targetSum,HashMap<Long, Integer> h, int count) {
        if (node == null)
            return;

        // The current prefix sum
        currSum += node.val;

        // Here is the sum we're looking for
        if (currSum == targetSum)
            count++;

        // The number of times the curr_sum − k has occurred already,
        // determines the number of times a path with sum k
        // has occurred up to the current node
        count += h.getOrDefault(currSum - targetSum, 0);

        //Add the current sum into the hashmap
        // to use it during the child node's processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // Process the left subtree
        preorder(node.left, currSum,targetSum,h,count);

        // Process the right subtree
        preorder(node.right, currSum,targetSum,h,count);

        // Remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }

}
