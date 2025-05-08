package com.company.BinaryTree.BST;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BSTIterator_O_N_SC {
    List<Integer> list = new ArrayList<>();
    int index = 0;

    public BSTIterator_O_N_SC(TreeNode root) {
        inorder(root);
    }

    private void inorder(TreeNode root){
        if(root == null){
            return;
        }

        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }
}

class BSTIterator {
    Stack<TreeNode> order = new Stack<>();

    public BSTIterator(TreeNode root) {
        // partialOrder
        partialInorder(root);
    }

    private void partialInorder(TreeNode root) {
        while(root!=null){
            order.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode top = order.pop();
        partialInorder(top.right);
        return top.val;
    }

    public boolean hasNext() {
        return !order.isEmpty();
    }
}
