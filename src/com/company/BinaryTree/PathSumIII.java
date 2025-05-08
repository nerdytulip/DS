package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.HashMap;

public class PathSumIII {

    /**
     * https://leetcode.com/problems/path-sum-iii/description/?envType=study-plan-v2&envId=leetcode-75
     * https://www.youtube.com/watch?v=uZzvivFkgtM
     * */
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
    // O(n)
    public int pathSum(TreeNode root, int sum) {
        HashMap<Long, Integer> map = new HashMap<>();
        return preorder(root, 0L,sum,map);

    }

    public int preorder(TreeNode node, long currSum,int targetSum,HashMap<Long, Integer> map) {
        if (node == null)
            return 0;

        // Update the current prefix sum.
        currSum += node.val;
        int count = 0;

        // Explicitly check if the path from the root to the current node equals targetSum.
        if (currSum == targetSum) {
            count++;
        }

        // The number of times the curr_sum − k has occurred already,
        // determines the number of times a path with sum k
        // has occurred up to the current node
        count += map.getOrDefault(currSum - targetSum, 0);

        //Add the current sum into the hashmap
        // to use it during the child node's processing
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // Process the left subtree
        count += preorder(node.left, currSum,targetSum,map);

        // Process the right subtree
        count += preorder(node.right, currSum,targetSum,map);

        // Remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        map.put(currSum, map.get(currSum) - 1);

        return count;
    }
}
