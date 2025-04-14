package com.company.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class SameTree {
    /**
     * https://leetcode.com/problems/same-tree/description/
     *
     * modified level order to add null when chill does not exists
     * */

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode> queue = new LinkedList<>();

        queue.add(p);
        queue.add(q);

        while(!queue.isEmpty()){
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();

            if(first == null && second == null){
                continue;
            } else if(first == null || second == null || first.val != second.val){
                return false;
            }

            queue.add(first.left);
            queue.add(second.left);
            queue.add(first.right);
            queue.add(second.right);
        }

        return true;

    }
}
