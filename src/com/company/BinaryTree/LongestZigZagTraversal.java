package com.company.BinaryTree;

public class LongestZigZagTraversal {
    public int longestZigZag(TreeNode root) {
        return Math.max(dfs(root, true, 0), dfs(root, false, 0));
    }

    /**
     * dfs returns the maximum zigzag length found in the subtree rooted at `node`,
     * given that the current zigzag direction expectation is indicated by goLeft
     * and that so far the number of consecutive edges in the zigzag path is `steps`.
     */
    private int dfs(TreeNode node, boolean goLeft, int steps) {
        // When we hit a null node, the zigzag path stops.
        // We return steps - 1 because the last valid node is the end of the path.
        if (node == null) {
            return steps - 1;
        }

        // At a non-null node, start with the current steps.
        int currMax = steps;

        if (goLeft) {
            // If we expect to go left:
            // * Continue the zigzag on the left child (direction flips to false) and increment steps.
            // * Restart a new zigzag on the right child (direction remains true) with steps reset to 1.
            currMax = Math.max(currMax, dfs(node.left, false, steps + 1));
            currMax = Math.max(currMax, dfs(node.right, true, 1));
        } else {
            // If we expect to go right:
            // * Continue the zigzag on the right child (direction flips to true) and increment steps.
            // * Restart a new zigzag on the left child (direction remains false) with steps reset to 1.
            currMax = Math.max(currMax, dfs(node.right, true, steps + 1));
            currMax = Math.max(currMax, dfs(node.left, false, 1));
        }

        return currMax;
    }
}
