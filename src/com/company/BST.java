package com.company;

import com.sun.deploy.security.SelectableSecurityManager;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class BST {
    static class Node{
        int key;
        Node left,right,parent;
        Node(int key){
            this.key=key;
            left=right=null;
        }
    }

    static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.println(root.key+" ");
        inorder(root.right);
    }
    static Node insert(Node root,int key){
        if(root==null){
            return new Node(key);
        }
        if(key<root.key){
            root.left=insert(root.left,key);
        }
        else{
            root.right=insert(root.right,key);
        }
        return root;
    }

    static Node insertIterative(Node root,int key){
        Node curr =root;
        Node parent =null;
        if(root == null){
            return new Node(key);
        }
        while(curr!=null){
            parent =curr;
            if(key<curr.key)
                curr=curr.left;
            else
                curr=curr.right;
        }
        if(key<parent.key)
            parent.left=new Node(key);
        else
            parent.right=new Node(key);

        return root;
    }

    static void search(Node root,int key,Node parent){
        if(root==null){
            System.out.println("key not found");
            return;
        }
        if(root.key==key){
            if (parent==null){
                System.out.println("Node with key"+key+"is root node");
            }
            else if(key<parent.key){
                System.out.println("given key is left node with key"+parent.key);
            }
            else {
                System.out.println("given key is right node with key "+parent.key);
            }
            return;
        }
        if(key<root.key){
            search(root.left,key,root);
        }
        else {
            search(root.right,key,root);
        }

    }

    static boolean search(Node root,int key){
        while(root!=null){
            if(key<root.key)
                root = root.left;
            else if(key>root.key)
                root = root.right;
            else
                return true;
        }
        return false;
    }
    static Node minimumKey(Node curr){
        while(curr.left!=null){
            curr=curr.left;
        }
        return curr;
    }

    static Node deleteNode(Node root,int key){
        Node parent =null;
        Node curr =root;
        while(curr!=null && curr.key!=key){
            parent =curr;
            if(key<curr.key)
                curr =curr.left;
            else
                curr =curr.right;
        }
        if(curr==null)
            return root;
        //case 1:Node to be deleted has no children
        if(curr.left==null &&curr.right==null){
            //if node to be deletd is not a root node,
            //then set its left/right child to null
            if(curr!=root){
                if(parent.left==curr)
                    parent.left=null;
                else
                    parent.right=null;
            }
        }

        //case 2 Node to be deleted has two children
        else if(curr.left!=null && curr.right!=null){
            //find its in-order successor node
            Node successor =minimumKey(curr.right);
            int val = successor.key;
            deleteNode(root,successor.key);
            curr.key=val;
        }

        //case 3:Node to be deleted has only one child
        else{
            Node child =(curr.left!=null)?curr.left:curr.right;
            if(curr !=root){
                if(curr == parent.left)
                    parent.left=child;
                else
                    parent.right=child;
            }
            else{
                root =child;
            }
        }
        return root;
    }
    /*@https://www.techiedelight.com/construct-balanced-bst-given-keys/*/
    static Node convert(int keys[],int low,int high,Node root){
        if(low>high)
            return root;
        int mid =(low+high)/2;
        root=new Node(keys[mid]);
        root.left=convert(keys,low,mid-1,root.left);
        root.right=convert(keys,mid+1,high,root.right);

        return root;
    }
    static Node convert(int keys[]){
        Arrays.sort(keys);
        return convert(keys,0,keys.length-1,null);
    }
    static boolean isBST(Node node,int minKey,int maxKey){
        if(node==null)
            return true;
        if(node.key<minKey || node.key>maxKey)
            return false;
        return isBST(node.left,minKey,node.key) &&
                isBST(node.right,node.key,maxKey);
    }

    /*@https://www.techiedelight.com/determine-given-binary-tree-is-a-bst-or-not/*/
    static void isBST(Node root){
        if(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE)){
            System.out.println("this is a BST");
        }
        else{
            System.out.println("this is not a BST");
        }
    }


    //basically this is inorder traversal of tree and we stop when we have traversed k keys
    static int Kthsmallest(Node root,AtomicInteger i,int k){
        if(root==null)
            return Integer.MAX_VALUE;
        int left=Kthsmallest(root.left,i,k);

        //if kth smallest is found in left subtree,return it
        if(left!=Integer.MAX_VALUE)
            return left;
        if(i.incrementAndGet()==k){
            return root.key;
        }
        return Kthsmallest(root.right,i,k);
    }

    static int Kthsmallest(Node root,int k){
        AtomicInteger i = new AtomicInteger(0);
       return Kthsmallest(root,i,k);
    }
    public static class Count{
        int c =0;
    }
    /*
    static int Kth_largest(Node root,int k,Count C){
        if(root ==null|| C.c>=k){
            return Integer.MIN_VALUE;
        }
        int right=Kth_largest(root.right,k,C);
        if(right!=Integer.MIN_VALUE)
            return right;

        //increment the count of visited nodes
        C.c++;
        if(C.c==k)
            return root.key;
        return Kth_largest(root.left,k,C);
    }
    static int Kth_largest(Node root,int k){
        Count c = new Count();
        return Kth_largest(root,k,c);
    }*/

    //this follows reverse inorder traversal
    static int Kthlargest(Node root,AtomicInteger i,int k){
        if(root ==null)
            return Integer.MIN_VALUE;
        int right = Kthlargest(root.right,i,k);
        if(right != Integer.MIN_VALUE)
            return right;
        if(i.incrementAndGet()==k){
            return root.key;
        }
        return Kthlargest(root.left,i,k);

    }

    static int Kthlargest(Node root,int k){
        AtomicInteger i = new AtomicInteger(0);
        return Kthlargest(root,i,k);
    }

    static Node LCA(Node root,int n1,int n2){
        if(root == null)
            return null;
        if(root.key>n1 && root.key>n2)
            return LCA(root.left,n1,n2);
        if(root.key<n1 && root.key<n2)
            return LCA(root.right,n1,n2);
        return root;
    }

    //https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
    static Node inordersuccessor_using_pointers(Node root,Node n){
        if(n.right!=null){
            return minimumKey(n.right);
        }
        Node succ = null;
        while(root!=null){
            if(n.key<root.key){
                succ = root;
                root = root.left;
            }else if(n.key>root.key)
                root=root.right;
            else
                break;
        }
        return succ;
    }

    static ArrayList<Integer> treeToList(Node node,ArrayList<Integer> list){
        if(node == null)
            return list;
        treeToList(node.left,list);
        list.add(node.key);
        treeToList(node.right,list);
        return list;
    }
    static boolean isPairPresent(Node root,int target){
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = treeToList(root,a1);
        int start=0;
        int end=a2.size()-1;
        while (start<end){
            if(a2.get(start)+a2.get(end)==target){
                System.out.println("pair found:"+a2.get(start)+"+"+a2.get(end)+"="+target);
                return true;
            }
            if(a2.get(start)+a2.get(end)>target)
                end--;
            if(a2.get(start)+a2.get(end)<target)
                start++;
        }
        System.out.println("no such values are found");
        return false;
    }

    static int maxelpath(Node q,int x){
        Node p = q;
        int mx=-1;
        while(p.key!=x){
            if(p.key>x){
                mx = Math.max(mx,p.key);
                p=p.left;
            }
            else{
                mx = Math.max(mx,p.key);
                p=p.right;
            }
        }
        return Math.max(mx,x);
    }

    /*https://www.geeksforgeeks.org/maximum-element-two-nodes-bst/*/
    static int maximum_element_between_two_node(Node root,int x,int y ){
        Node p = root;
        while((x<p.key && y<p.key)|| (x>p.key && y>p.key)){
            if(x<p.key && y<p.key)
                p=p.left;
            else if(x>p.key && y>p.key)
                p=p.right;
        }
        return Math.max(maxelpath(p,x),maxelpath(p,y));
    }

    static class Value{
        int max_size =0;
        boolean is_bst = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }

    static int largestBST(Node root,Value min_ref,Value max_ref,
                          Value max_size,Value is_bst_ref){
        if(root==null){
            is_bst_ref.is_bst=true;
            return 0;
        }
        int min = Integer.MAX_VALUE;
        boolean left_flag = false;
        boolean right_flag = false;
        int ls,rs;
        max_ref.max=Integer.MIN_VALUE;
        ls=largestBST(root.left,min_ref,max_ref,max_size,is_bst_ref);
        if(is_bst_ref.is_bst == true && root.key>max_ref.max){
            left_flag=true;
        }

        min =min_ref.min;
        min_ref.min = Integer.MAX_VALUE;
        rs=largestBST(root.right,min_ref,max_ref,max_size,is_bst_ref);
        if(is_bst_ref.is_bst==true && root.key<min_ref.min){
            right_flag=true;
        }

        if(min<min_ref.min)
            min_ref.min=min;
        if(root.key<min_ref.min)
            min_ref.min =root.key;
        if(root.key>max_ref.max)
            max_ref.max=root.key;

        if(left_flag && right_flag) {
            if (ls + rs + 1 > max_size.max_size)
                max_size.max_size = ls + rs + 1;
            return ls + rs + 1;
        }else{
            is_bst_ref.is_bst=false;
            return 0;
        }
    }

    /*@https://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/*/
    static  int largestBST(Node root){
        Value val = new Value();
        largestBST(root,val,val,val,val);
        return val.max_size;
    }
    static int distancefromroot(Node root,int x){
        if(root.key==x)
            return 0;
        else if(root.key>x)
            return 1+distancefromroot(root.left,x);
        return 1+distancefromroot(root.right,x);
    }
    /*@https://www.geeksforgeeks.org/shortest-distance-between-two-nodes-in-bst/*/
    static  int shortest_distanceBetween2(Node root,int a,int b){
        if(root == null)
            return 0;
        if(root.key>a && root.key>b)
            return shortest_distanceBetween2(root.left,a,b);
        if(root.key<a && root.key<b)
            return shortest_distanceBetween2(root.right,a,b);
        if (root.key >= a && root.key <= b)
            return distancefromroot(root,a)+distancefromroot(root,b);
        return 0;
    }
    static void storeinorder(Node root,ArrayList<Integer> l){
        if (root==null)
            return;
        storeinorder(root.left,l);
        l.add(root.key);
        storeinorder(root.right,l);
    }
    static void pairSum_util(ArrayList<Integer> l1,ArrayList<Integer> l2,int sum){
        int left=0;
        int right=l2.size()-1;
        while(left<l1.size() && right>=0){
            if (l1.get(left)+l2.get(right)==sum){
                System.out.println( "(" +l1.get(left) + ", "+ l2.get(right) + "), ");
                left++;
                right--;
            }
            // If sum is more, move to higher value in
            // first Vector.
            else if (l1.get(left) + l2.get(right) < sum)
                left++;

                // If sum is less, move to lower value in
                // second Vector.
            else
                right--;
        }
    }

    static void pairsum(Node root1,Node root2,int sum){
        ArrayList<Integer> l1=new ArrayList<>();
        ArrayList<Integer> l2=new ArrayList<>();
        storeinorder(root1,l1);
        storeinorder(root2,l2);
        pairSum_util(l1,l2,sum);
    }

    //catalan number,given n keys from 1 to n how many distinct bst can be formed
    /*https://www.youtube.com/watch?v=YDf982Lb84o*/
    /*https://www.youtube.com/watch?v=GgP75HAvrlY*/
    /*https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CountNumberOfTreesInBST.java*/
    /*this approach is dynamic progarmming as we can divide the problem in
    * sub-problems and use them for future calculations
    * TC-O(n^2) SC-O(n)*/
   static int countTrees(int n){
       int T[]=new int[n+1];
       T[0]=1;
       T[1]=1;
       for (int i=2;i<=n;i++){
           for (int j=0;j<i;j++){
               T[i]+=T[j]*T[i-j-1];
           }
       }
       return T[n];
   }
   static int countTreesRec(int numkeys){
       if(numkeys<=1)
           return (1);
       else {
           int sum=0;
           int left,right,root;
           for (root=1;root<=numkeys;root++){
               left=countTreesRec(root-1);//i-1
               right=countTreesRec(numkeys-root);//n-i
               sum+=left*right;
           }
           return (sum);
       }
   }
    public static void main(String[] args){
        Node root =null;
        int keys[] ={15,10,20,8,12,16,25};
        for(int key:keys){
            root=insert(root,key);
        }
        inorder(root);

        int k =2;
        int res=Kthsmallest(root,k);
        if(res!=Integer.MAX_VALUE){
            System.out.println(res);
        }
        else {
            System.out.println("invalid input");
        }

        int k1 =2;
        int res1=Kthlargest(root,k1);
        if(res1!=Integer.MIN_VALUE){
            System.out.println(res1);
        }
        else {
            System.out.println("invalid input");
        }
        /*
        int res2=Kth_largest(root,k1);
        if(res1!=Integer.MIN_VALUE){
            System.out.print(res2);
        }
        else {
            System.out.println("invalid input");
        }*/



        //inorder(root);

       /* Node root =convert(keys);
        inorder(root);*/
       int n1=20 ,n2=8;
       Node t = LCA(root,n1,n2);

       System.out.println("lca of"+n1+"and"+n2+"is"+t.key);

        Node root1 = null;
        root1 = insert(root1, 8);
        root1 = insert(root1, 10);
        root1 = insert(root1, 3);
        root1 = insert(root1, 6);
        root1 = insert(root1, 1);
        root1 = insert(root1, 5);
        root1 = insert(root1, 7);
        root1 = insert(root1, 14);
        root1 = insert(root1, 13);

        Node root2 = null;
        root2 = insert(root2, 5);
        root2 = insert(root2, 18);
        root2 = insert(root2, 2);
        root2 = insert(root2, 1);
        root2 = insert(root2, 3);
        root2 = insert(root2, 4);
        pairsum(root1,root2,10);
        int N=4;
        System.out.println("N :"+countTreesRec(N));
    }
}
