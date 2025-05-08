package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DiameterOfBinaryTree {

    // recursive method
    int max = 0;
    private int diameterOfBinaryTree(TreeNode root) {
        max = 1;
        depth(root);
        return max - 1;
    }

    private int depth(TreeNode node)
    {
        if(node == null)
        {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // update the answer, because diameter of a
        // tree is nothing but maximum value of
        // (left_depth + right_depth + 1) for each node
        max = Math.max(max, leftDepth + rightDepth + 1);
        return 1 + Math.max(leftDepth, rightDepth);
    }


    // Iterative method - recursive solution is also correct - but difficult to debug
    public int diameterOfBinaryTree_Iterative(TreeNode root){
        Map<TreeNode,Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();

        int diameter = 0;

        if(root!=null){
            stack.push(root);
        }

        // fill up stack to do post order traversal
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();

            if(node.left!=null && !map.containsKey(node.left)){
                stack.push(node.left);
            }else if(node.right!=null && !map.containsKey(node.right)){
                stack.push(node.right);
            }else{
                stack.pop();
                int leftDepth = map.getOrDefault(node.left,0);
                int rightDepth = map.getOrDefault(node.right,0);

                map.put(node,1 + Math.max(leftDepth,rightDepth));

                diameter = Math.max(diameter,leftDepth+rightDepth);
            }
        }

        return diameter;
    }
}
