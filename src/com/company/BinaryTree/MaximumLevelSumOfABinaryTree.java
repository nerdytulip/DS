package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaximumLevelSumOfABinaryTree {
    /**
     * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
     * */

    public int maxLevelSum(TreeNode root) {
        int maxSum = Integer.MIN_VALUE;
        int ansLevel = 0;
        int currentLevel = 0;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            currentLevel++;
            int sumAtCurrLevel = 0;

            int size = q.size();
            for(int i = 0; i< size; i++){
                TreeNode node = q.poll();
                sumAtCurrLevel+= node.val;

                if(node.left!=null){
                    q.offer(node.left);
                }

                if(node.right!=null){
                    q.offer(node.right);
                }
            }

            if(sumAtCurrLevel>maxSum){
                maxSum = sumAtCurrLevel;
                ansLevel = currentLevel;
            }
        }

        return ansLevel;
    }
}
