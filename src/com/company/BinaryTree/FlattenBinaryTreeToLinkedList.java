package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    /**
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=NOKVBiJwkD0
     * */
    public void flattenRec(TreeNode root) {
        if(root == null){
            return;
        }

        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;

        root.left = null;

        flattenRec(tempLeft);
        flattenRec(tempRight);

        root.right = tempLeft;
        TreeNode current = root;
        while(current.right!=null){
            current = current.right;
        }

        current.right = tempRight;
    }

    public void flatten(TreeNode root){
        if(root == null){
            return;
        }

        while(root!=null){
            if(root.left!=null) {
                //go to rightmost child
                TreeNode left = root.left;
                TreeNode current = left;
                while (current.right != null) {
                    current = current.right;
                }

                current.right = root.right;

                root.left = null;
                root.right = left;
            }
            root = root.right;
        }
    }
}
