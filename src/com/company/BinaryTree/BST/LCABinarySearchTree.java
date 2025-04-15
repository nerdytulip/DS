package com.company.BinaryTree.BST;

import com.company.BinaryTree.TreeNode;

public class LCABinarySearchTree {
    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
     * */
    public TreeNode lowestCommonAncestorRec(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        if(root.equals(p) || root.equals(q)){
            return root;
        }

        if(p.val>root.val && q.val>root.val){
           return lowestCommonAncestorRec(root.right,p,q);
        } else if(p.val<root.val && q.val<root.val){
            return lowestCommonAncestorRec(root.left,p,q);
        }else{
            return root;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                // Both nodes are in right subtree
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                // Both nodes are in left subtree
                root = root.left;
            } else {
                // Either we found a split, or one of p or q is the current node
                return root;
            }
        }
        return null; // Should never happen if p and q are in the tree
    }

}
