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
        }else{
            // found the node where we have to delete
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            }

            // Case 2: Node with two children.
            // Find the smallest node in the right subtree (inorder successor).
            TreeNode minNode = findMin(root.right);

            // Replace current node's value with the inorder successor's value.
            root.val = minNode.val;

            // Delete the inorder successor from the right subtree.
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
