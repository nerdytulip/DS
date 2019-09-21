package com.company;

import java.util.LinkedList;
import java.util.Queue;

class Node{
    int key;
    Node left,right;
    public Node(int item){
        key=item;
        right=left=null;
    }
}

public class binarytree {
    static class Node{
        int key;
        Node left,right;
        Node(int key){
            this.key=key;
            left=right=null;
        }
    }

    static Node root;
    static Node temp=root;
    static void insert(Node temp,int key){
        Queue<Node> q = new LinkedList<>();
        q.add(temp);
    }


    public static void main(String[] args){
        binarytree tree = new binarytree();
        tree.root=new Node(1);
        tree.root.left=new Node(2);
        tree.root.right=new Node(3);
        tree.root.left.left= new Node(4);
    }
}
