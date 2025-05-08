package com.company.BinaryTree.BST;

import com.company.BinaryTree.NodeDTO.TreeNode;

public class SearchInABST {

    // recursive solution - TC- O(H) and SC - O(H)
    public TreeNode searchBSTRec(TreeNode root, int val) {
        if(root == null || root.val == val){
            return root;
        }

        if(val<root.val){
            return searchBST(root.left,val);
        }else{
            return searchBST(root.right,val);
        }
    }

    // iterative solution - TC - O(H) and SC - O(1)
    public TreeNode searchBST(TreeNode root, int val) {
        while(root!=null && val!=root.val){
            if(val<root.val){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return root;
    }
}
