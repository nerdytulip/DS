package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class CountGoodNodesInABinaryTree {
    /**
     * https://leetcode.com/problems/count-good-nodes-in-binary-tree/?envType=study-plan-v2&envId=leetcode-75
     * */
    public int goodNodes(TreeNode root) {
        return countGoodNodes(root,root.val);
    }

    private int countGoodNodes(TreeNode node, int maxSoFar){
        int numOfGoodNodes = 0;
        if(node == null){
            return 0;
        }

        // number of good nodes in child tree
        if(node.val >= maxSoFar){
            maxSoFar = node.val;
            numOfGoodNodes++;
        }

        numOfGoodNodes+= countGoodNodes(node.left,maxSoFar) + countGoodNodes(node.right,maxSoFar);

        return numOfGoodNodes;
    }
}
