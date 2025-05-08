package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class LCABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        if(root.equals(p) || root.equals(q)){
            return root;
        }

        TreeNode leftAns = lowestCommonAncestor(root.right,p,q);
        TreeNode rightAns = lowestCommonAncestor(root.left,p,q);

        if(leftAns == null){
            return rightAns;
        } else if(rightAns == null){
            return leftAns;
        }
        return root;
    }
}
