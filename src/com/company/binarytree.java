package com.company;

import java.util.*;
import java.util.LinkedList;

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
    //inorder-(left,root,right)
    static void inorder(Node temp){
        if(temp==null)
            return;
        inorder(temp.left);
        System.out.print(temp.key+" ");
        inorder(temp.right);
    }
    //preorder-(root,left,right)
    static boolean isIdentical(Node x,Node y){
        if(x==null && y==null){
            return true;
        }
        else {
            return (x.key==y.key && isIdentical(x.left,y.left) && isIdentical(x.right,y.right));
        }
    }
    static void insert(Node temp,int key){
        Queue<Node> q = new ArrayDeque<>();
        q.add(temp);
        while(!q.isEmpty()){
            temp=q.peek();
            q.remove();
            if(temp.left==null){
                temp.left=new Node(key);
                break;
            }else{
                q.add(temp.left);
            }
            if(temp.right==null){
                temp.right=new Node(key);
                break;
            }else{
                q.add(temp.right);
            }
        }
    }

    static void deletedeep(Node temp,Node deep){
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);
        while (!q.isEmpty()){
            temp=q.peek();
            q.remove();
            if(temp.right==deep){
                temp.right=null;
                return;
            }else{
                q.add(temp.right);
            }
            if(temp.left==deep){
                temp.left=null;
                return;
            }else{
                q.add(temp.left);
            }
        }
    }

    static void delete(Node temp,int data){
        Node temp3=temp;
        Node temp2=null;
        if(temp.left==null && temp.right==null){
            temp=null;
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);
        while(!q.isEmpty()){
            temp=q.peek();
            q.remove();
            if(temp.key==data){
                temp2=temp;
            }
            if(temp.left!=null)
                q.add(temp.left);
            if(temp.right!=null)
                q.add(temp.right);
        }
        int x = temp.key;
        deletedeep(temp3,temp);
        temp2.key=x;
    }

    static int height(Node root){
        if(root == null){
            return 0;
        }
        return 1+Math.max(height(root.left),height(root.right));
    }
    static boolean printlevel(Node root,int level){
       // System.out.println("root"+root.key+"level"+level);
        if(root==null){
            return false;
        }
        if(level ==1){
            System.out.print(root.key+" ");
            return true;
        }
        boolean left = printlevel(root.left,level-1);
        boolean right = printlevel(root.right,level-1);
        return left||right;
    }
    //also called BFS,O(n^2)
    static void LevelOrderTraversal(Node root){
        int level=1;
        while(printlevel(root,level)){
            level++;
        }
    }
    //BFS uses queue,TC-O(n) and SC-O(n)
    static void BFS(Node root){
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        Node curr;//pointer to store current node
        while(!q.isEmpty()){
            //poll() retrieves and removes head of the queue
            curr=q.poll();
            System.out.print(curr.key+" ");
            if(curr.left!=null){
                q.add(curr.left);
            }
            if(curr.right!=null){
                q.add(curr.right);
            }
        }
    }

    static void DFS(Node root){
        if(root==null)
            return;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.key+" ");
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
    }

    static void reverseLevelOrderTraversal(Node root){
        if(root==null){
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        Deque<Integer> stack = new ArrayDeque<>();
        Node curr;
        while (!q.isEmpty()){
            curr = q.poll();
            stack.push(curr.key);
            if(curr.left!=null){
                q.add(curr.left);
            }
            if(curr.right!=null){
                q.add(curr.right);
            }
        }
        while(!stack.isEmpty()){
            System.out.print(stack.poll()+" ");
        }
    }
    static Node findRightNode(Node root,int val){
        if(root==null){
            return null;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        Node curr;
        while(!q.isEmpty()){
            int size = q.size();
            System.out.println("size"+size);
            while(size-- >0){
                curr = q.poll();
                if(curr.key==val){
                    if(size==0){
                        return null;
                    }
                    return q.peek();
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
            }
        }
        return null;
    }

    /*@https://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/*/
    static class Maximum{
        int max_no=Integer.MIN_VALUE;
    }

    static class Minimum{
        int min_no=Integer.MAX_VALUE;
    }

    static Node target_leaf=null;

    static boolean printPath(Node root,Node target_leaf){
        if(root ==null)
            return false;
        // return true if this node is the target_leaf or
        // target leaf is present in one of its descendants
        if(root==target_leaf||printPath(root.left,target_leaf)
                || printPath(root.right,target_leaf)){
            System.out.println(root.key+" ");
            return true;
        }
        return false;
    }
    static void getTargetLeaf(Node root,Maximum maxsum_ref,int currsum){
        if(root ==null)
            return ;
        currsum=currsum+root.key;
        if(root.left==null && root.right==null){
            if(currsum>maxsum_ref.max_no){
                maxsum_ref.max_no=currsum;
                target_leaf=root;
            }
        }
        getTargetLeaf(root.left,maxsum_ref,currsum);
        getTargetLeaf(root.right,maxsum_ref,currsum);
    }

    //Given a Binary Tree, find the maximum sum path from a leaf to root
    static int maxSumPath_leaf_to_node(Node root){
        Maximum max = new Maximum();
        if(root ==null)
            return 0;
        getTargetLeaf(root,max,0);
        printPath(root,target_leaf);
        return max.max_no;
    }
    /*@https://www.techiedelight.com/find-maximum-sum-path-between-two-leaves-in-a-binary-tree/*/
    /*@https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/*/
    static int maxsumpath(Node root,Maximum maxsum_ref){
        if(root == null)
            return 0;
        int left = maxsumpath(root.left,maxsum_ref);
        int right=maxsumpath(root.right,maxsum_ref);
        int currsum=left+right+root.key;
        maxsum_ref.max_no = Math.max(currsum,maxsum_ref.max_no);

        return Math.max(left,right)+root.key;
    }
    /*@https://www.techiedelight.com/find-maximum-sum-path-between-two-leaves-in-a-binary-tree/*/
    /*@https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/*/
    static int maxSumPath_between_two_leaf(Node root){
        Maximum max = new Maximum();
        if(root ==null)
            return 0;
        maxsumpath(root,max);
        return max.max_no;
    }
    static int minsumpath(Node root,Minimum minsum_ref){
        if(root ==null)
            return 0;
        if(root.left==null && root.right==null)
            return root.key;
        int ls = minsumpath(root.left,minsum_ref);
        int rs=minsumpath(root.right,minsum_ref);
        if(root.left!=null && root.right!=null){
            int currsum=ls+rs+root.key;
            minsum_ref.min_no=Math.min(minsum_ref.min_no,currsum);
            // Return minimum possible value for root being
            // on one side
            return Math.min(ls+root.key,rs+root.key);
        }
        if(root.left==null)
            return rs+root.key;
        else
            return ls+root.key;
    }

    static int minSumPath_between_two_leaf(Node root){
        Minimum min = new Minimum();
        if(root ==null)
            return 0;
        minsumpath(root,min);
        return min.min_no;
    }
    static class A{
        int ans = Integer.MIN_VALUE;
    }

    static int height(Node root,A a){
        if(root == null)
            return 0;
        int lh = height(root.left,a);
        int rh=height(root.right,a);
        a.ans=Math.max(a.ans,1+lh+rh);
        return 1+Math.max(lh,rh);
    }
    /*https://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/*/
    static String diameter(Node root){
        String S = "";
        if(root == null)
            return S;
        A a =new A();
        int height_of_tree = height(root,a);

        S=S+"height of tree"+height_of_tree+"and diameter"+a.ans;
        return S;
    }

    public static void main(String[] args){
        /*root=new Node(10);
        root.left=new Node(11);
        root.left.left=new Node(7);
        root.left.right=new Node(12);
        root.right=new Node(9);
        root.right.left=new Node(15);
        root.right.right=new Node(8);
        int key =13;
        insert(root,key);*/
       /* System.out.println("inorder before printing");
        inorder(root);
        int key=12;
        insert(root,key);
        System.out.println("indorder after adding key");
        inorder(root);
        delete(root,11);
        inorder(root);*/
        Node x = new Node(15);
        x.left = new Node(10);
        x.right = new Node(20);
        x.left.left = new Node(8);
        x.left.right = new Node(12);
        x.right.left = new Node(16);
        x.right.right = new Node(25);

        // construct second tree
        Node y = new Node(15);
        y.left = new Node(10);
        y.right = new Node(20);
        y.left.left = new Node(8);
        y.left.right = new Node(12);
        y.right.left = new Node(16);
        y.right.right = new Node(25);

        root = new Node(-15);
        root.left = new Node(5);
        root.right = new Node(6);
        root.left.left = new Node(-8);
        root.left.right = new Node(1);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(6);
        root.right.left = new Node(3);
        root.right.right = new Node(9);
        root.right.right.right = new Node(0);
        root.right.right.right.left = new Node(4);
        root.right.right.right.right = new Node(-1);
        root.right.right.right.right.left = new Node(10);

        Node root1 = new Node(4);
        root1.left=new Node(5);
        root1.right=new Node(-6);
        root1.left.left = new Node(2);
        root1.left.right = new Node(-3);
        root1.right.left = new Node(1);
        root1.right.right = new Node(8);

        Node root2=new Node(1);
        root2.left=new Node(2);
        root2.right=new Node(3);
        root2.left.left=new Node(4);
        root2.left.right=new Node(5);

        System.out.println("inorder traversal");
        inorder(root1);
        if (isIdentical(x, y)) {
            System.out.println("Given binary Trees are identical");
        } else {
            System.out.println("Given binary Trees are not identical");
        }

        System.out.println("height of binary tree is"+height(root));
        System.out.println("using level order function");
        LevelOrderTraversal(root);
        System.out.println("using BFS");
        BFS(root);
        System.out.println("reverse level order");
        reverseLevelOrderTraversal(root);
        System.out.println("findright node function");
        Node right = findRightNode(root,12);
        if(right !=null){
            System.out.println("right node is "+right.key);
        }
        else{
            System.out.println("right node doesnt exist");
        }
        System.out.println(maxSumPath_leaf_to_node(root));
        System.out.println("maxsumpath"+ maxSumPath_between_two_leaf(root));
        System.out.println("minsumpath"+minSumPath_between_two_leaf(root1));
        System.out.println(diameter(root2));
    }
}
