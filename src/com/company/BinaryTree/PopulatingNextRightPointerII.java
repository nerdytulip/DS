package com.company.BinaryTree;

import com.company.BinaryTree.NodeDTO.Node;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointerII {

    /**
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=yl-fdkyQD8A
     * */

    // TC - O(N) and SC - O(N)
    public Node connect_With_Queue(Node root) {
        if(root == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();
            Node dummy = new Node(0);

            for(int i=0;i<size;i++){
                Node node = q.remove();
                dummy.next = node;
                dummy = dummy.next;

                if(node.left!=null){
                    q.add(node.left);
                }

                if(node.right!=null){
                    q.add(node.right);
                }
            }
        }

        return root;
    }

    public Node connect(Node root) {
        if(root == null) return null;

        Node head = root;

        while (head!=null){
            Node dummy = new Node(0);
            Node temp = dummy;

            while(head!=null){
                if(head.left!=null){
                    temp.next = head.left;
                    temp = temp.next;
                }

                if(head.right!=null){
                    temp.next = head.right;
                    temp = temp.next;
                }
                head = head.next;
            }
            head = dummy.next;
        }
        return root;
    }
}
