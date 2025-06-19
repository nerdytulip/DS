package com.company.BinaryTree.BST;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class DeleteANodeFromBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return root;
        }

        if(key> root.val){
            root.right = deleteNode(root.right,key);
        }else if(key < root.val){
            root.left = deleteNode(root.left,key);
        }// Node to be deleted is found
        else {
            // Case 1: Node has no left child
            if (root.left == null) {
                return root.right; // Replace node with right child (could be null or a subtree)
            }
            // Case 1: Node has no right child
            else if (root.right == null) {
                return root.left; // Replace node with left child
            }

            // Case 2: Node has two children
            // Find the inorder successor (smallest value in right subtree)
            TreeNode minNode = findMin(root.right);

            // Copy the inorder successor's value to the current node
            root.val = minNode.val;

            // Recursively delete the inorder successor from right subtree
            root.right = deleteNode(root.right, minNode.val);
        }
        return root;
    }

    // Helper function to find the minimum value node in a subtree.
    private TreeNode findMin(TreeNode node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}
