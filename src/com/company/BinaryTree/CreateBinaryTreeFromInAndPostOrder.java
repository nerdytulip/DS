package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class CreateBinaryTreeFromInAndPostOrder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inOrderIndexMap.put(inorder[i], i);

        return buildSubtree(postorder, inOrderIndexMap,
                postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildSubtree(int[] postorder, Map<Integer, Integer> inOrderIndexMap,
                                  int rootIndex, int left, int right) {
        if (left > right)
            return null;

        TreeNode root = new TreeNode(postorder[rootIndex]);
        int mid = inOrderIndexMap.get(postorder[rootIndex]);

        // Build right subtree first (since root is at end in postorder)
        root.right = buildSubtree(postorder, inOrderIndexMap,
                rootIndex - 1, mid + 1, right);

        // Skip right subtree size elements to get to the left subtree's root
        int rightSubtreeSize = right - mid;
        root.left = buildSubtree(postorder, inOrderIndexMap,
                rootIndex - rightSubtreeSize - 1, left, mid - 1);

        return root;
    }
}
