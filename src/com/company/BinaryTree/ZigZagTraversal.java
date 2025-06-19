package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.*;

public class ZigZagTraversal {
    /**
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
     * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzag = new ArrayList<>();

        if (root == null) {
            return zigzag; // Return empty list if the tree is empty
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false; // true to mark it is reverse

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            Stack<Integer> reverseStack = new Stack<>();

            for(int i =0;i<size;i++){
                TreeNode node = queue.poll();

                if(flag){
                    reverseStack.add(node.val);
                }else{
                    level.add(node.val);
                }

                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }

            flag = !flag;

            while(!reverseStack.isEmpty()){
                level.add(reverseStack.pop());
            }

            zigzag.add(level);
        }

        return zigzag;
    }
}
