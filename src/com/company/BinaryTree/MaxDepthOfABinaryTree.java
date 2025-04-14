package com.company.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxDepthOfABinaryTree {
    //https://www.baeldung.com/cs/binary-tree-height
    /**
     * ALso called Height of binary tree , or number of levels in a BT
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
     * */
    public int heightOfBinaryTree(TreeNode root){
        if(root == null){
            return 0;
        }

        Deque<TreeNode> treeNodeQueue = new ArrayDeque<>();
        treeNodeQueue.add(root);

        int numberofLevels = 0;

        while(true){
            int nodeCountAtLevel = treeNodeQueue.size();
            if(nodeCountAtLevel == 0){
                //if we find no nodes at level , it means tree has ended so return
                return numberofLevels;
            }

            while(nodeCountAtLevel>0){
                TreeNode element = treeNodeQueue.poll();

                if(element.left!=null){
                    treeNodeQueue.add(element.left);
                }
                if(element.right!=null){
                    treeNodeQueue.add(element.right);
                }
                nodeCountAtLevel--;
            }
            numberofLevels++;
        }
    }

    // another solution
    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        int lh=maxDepth(root.left);
        int rh=maxDepth(root.right);
        return 1+Math.max(lh,rh);
    }
}
