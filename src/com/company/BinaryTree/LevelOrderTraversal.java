package com.company.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class LevelOrderTraversal {
    /**
     * Also called BFS
     * */
    public void levelOrderTravsersal(TreeNode root){
        Deque<TreeNode> treeNodeQueue = new ArrayDeque<>();

        treeNodeQueue.add(root);

        while(!treeNodeQueue.isEmpty()){
            TreeNode treeNode = treeNodeQueue.poll();

            System.out.println(treeNode.val + " -> ");

            if(treeNode.left!=null){
                treeNodeQueue.add(treeNode.left);
            }

            if(treeNode.right!=null){
                treeNodeQueue.add(treeNode.right);
            }
        }
    }
}
