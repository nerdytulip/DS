package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String fullTree = preOrderTraversal(root);
        String subTree = preOrderTraversal(subRoot);

        return fullTree.contains(subTree);
    }

    String preOrderTraversal(TreeNode node){
        if(node == null){
            return "null";
        }

        StringBuilder sb = new StringBuilder("^");
        sb.append(node.val);
        sb.append(preOrderTraversal(node.left));
        sb.append(preOrderTraversal(node.right));

        return sb.toString();
    }
}
