package com.company.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {

    /**
     * https://leetcode.com/problems/leaf-similar-trees/?envType=study-plan-v2&envId=leetcode-75
     * */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafNodesTree1 = new ArrayList<>();
        List<Integer> leafNodesTree2 = new ArrayList<>();

        preOrderTraversal(root1, leafNodesTree1);
        preOrderTraversal(root2, leafNodesTree2);

        return leafNodesTree1.equals(leafNodesTree2);
    }
    public void preOrderTraversal(TreeNode root,List<Integer> leafNodesTree){
        if(root == null){
            return;
        }
        if(root!=null && root.left == null && root.right == null){
            leafNodesTree.add(root.val);
        }

//        System.out.println(root.val);
        preOrderTraversal(root.left, leafNodesTree);
        preOrderTraversal(root.right, leafNodesTree);
    }
}
