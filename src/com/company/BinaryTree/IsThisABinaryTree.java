package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class IsThisABinaryTree {
    /**
     * https://www.hackerrank.com/challenges/is-binary-search-tree/problem
     * https://leetcode.com/problems/validate-binary-search-tree/description/
     *
     * */

    boolean isValidBST(TreeNode root) {
       List<Integer> inOrderList = new LinkedList<>();

       inorder(root, inOrderList);

       boolean isBST = true;
       int prev = inOrderList.get(0);

       for(int i =1;i< inOrderList.size();i++){
           if(inOrderList.get(i)<=prev){
               isBST = false;
           }
           prev = inOrderList.get(i);
       }
       return isBST;
    }

    private void inorder(TreeNode root, List<Integer> inOrderList) {
        if(root == null){
            return;
        }
        inorder(root.left,inOrderList);
        inOrderList.add(root.val);
        inorder(root.right,inOrderList);
    }
}
