package com.company;
import java.io.*;
import java.lang.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

class LinkedList {
     Node head;
     class Node{
        int data;
        Node next;
        Node(int d){data =d;next=null;}
    }
     void push(int newdata){
        Node newnode = new Node(newdata);
        //make next of new Node as head
        newnode.next=head;
        //move the head to point to new Node
        head = newnode;
    }
    void insertafter(Node Prevnode,int newdata){
        if(Prevnode==null){
            System.out.println("previous node cannot be null");
        }
        Node newnode = new Node(newdata);
        newnode.next=Prevnode.next;
        Prevnode.next=newnode;
    }
    void append(int newdata){
        Node newnode = new Node(newdata);
        if (head == null)
        {
            head = newnode;
            return;
        }
        newnode.next = null;
        Node last = head;
        while (last.next != null)
            last = last.next;
        last.next = newnode;
        return;
    }
    void traverse(){
        Node travenode=head;
        while(travenode != null){
            System.out.println(travenode.data);
            travenode = travenode.next;
        }
    }

    void deletelist(){
         head = null;
    }

    void deletenodebyvalue(int key){
         Node temp = head,prev =null;
         if(temp!=null && temp.data==key){
             head = temp.next;
             return;
         }
         while (temp!=null && temp.data!=key){
             prev = temp;
             temp = temp.next;
         }
         if(temp == null){
             return;
         }
         prev.next=temp.next;
    }

    void deletenodebyposition(int position){
         if(head == null)
             return;
         Node temp = head;
         if(position==0){
             head = temp.next;
             return;
         }
         for(int i =0;temp!=null && i<position-1;i++){
             temp=temp.next;
         }
         if(temp==null || temp.next==null)
             return;

       //  Node nextnode =temp.next.next;
        // temp.next=nextnode;
         temp.next=temp.next.next;
    }
    int getlength(){
        Node temp = head;
        int count = 0;
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }

    /*https://www.geeksforgeeks.org/in-a-linked-list-given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
    Fast solution is to copy the data from the next node to the node to be deleted and delete the next node*/
    void delete_node_using_only_pointer_to_node(Node node){
         Node temp =node.next;
         node.data=temp.data;
         node.next=temp.next;
    }

    boolean search(Node gnode,int x){
     Node givennode= gnode;
     while(givennode != null){
         if(givennode.data==x)
             return true;
         givennode=givennode.next;
     }
        return false;
    }

    int getNth(int index){
         Node current = head;
         int count =0;
         while(current!=null){
             if(count == index)
                 return current.data;
             count++;
             current=current.next;
         }
        assert(false);
         return 0;
    }
     void printNthFromLast(int n)
     {
         int len = 0;
         Node temp = head;
         while (temp != null) {
             temp = temp.next;
             len++;
         }
         if (len < n)
             return;
         temp = head;
         // 2) get the (len-n+1)th node from the beginning
         for (int i = 1; i < len - n + 1; i++)
             temp = temp.next;
         System.out.println(temp.data);
     }

     void getmid(){
         Node slowptr = head;
         Node fastptr =head;
         if(head != null){
             while(fastptr !=null && fastptr.next!=null){
                 fastptr=fastptr.next.next;
                 slowptr=slowptr.next;
             }
             System.out.println("mid element"+slowptr.data);
         }
     }

     int countoccur(int num){
         Node curr = head;
         int count=0;
         while(curr !=null){
             if(curr.data==num)
                 count++;
             curr=curr.next;
         }
         return count;
     }

     int detectLoop(){
         Node slowptr=head,fastptr=head;
         while(slowptr !=null && fastptr !=null & fastptr.next!=null){
             slowptr=slowptr.next;
             fastptr=fastptr.next.next;
             if(slowptr==fastptr){
                 System.out.println("Found loop");
                 return 1;
             }
         }
         return 0;
     }

     /*
     * Initialize three pointers prev as NULL, curr as head and next as NULL.
       Iterate trough the linked list. In loop, do following.
       // Before changing next of current,
        // store next node
        next = curr->next
        // Now change next of current
        // This is where actual reversing happens
        curr->next = prev

        // Move prev and curr one step forward
        prev = curr
        curr = next
     * */
     void reverselist(Node node){
         Node prev = null;
         Node current = node;
         Node next = null;
         while(current!= null){
             next = current.next;
             current.next=prev;
             prev=current;
             current=next;
         }
         node= prev;
     }

     /*@https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/*/
     Node reverse_in_groupsofk(Node head,int k){
         Node current=head;
         Node next=null;
         Node prev=null;
         int count =0;
         while(count<k && current!=null){
             next=current.next;
             current.next=prev;

             prev=current;
             current=next;
             count++;
         }

         if (next!=null){
             head.next=reverse_in_groupsofk(next,k);
         }
         return prev;
     }

     Node reverse_even_elements(Node head,Node prev){
         if (head == null)
             return null;
         Node next = null;
         Node current = head;
         while (current!=null && current.data%2==0){
             next=current.next;
             current.next=prev;
             prev=current;
             current=next;
         }
         if (current!=head){
             head.next=current;
             current=reverse_even_elements(current,null);
             return prev;
         }
         else{
             head.next=reverse_even_elements(head.next,head);
             return head;
         }

     }

     Node merge(Node h1,Node h2){
         if (h1==null)
             return h2;
         if (h2==null)
             return h1;
         if (h1.data<h2.data){
             h1.next=merge(h1.next,h2);
             return h1;
         }
         else{
             h2.next=merge(h1,h2.next);
             return h2;
         }
     }

     boolean ispalindrome(Node head){
         Node slow = head;
         boolean ispalin = true;
         Stack<Integer> stack = new Stack<Integer>();
         while(slow != null){
             stack.push(slow.data);
             slow=slow.next;
         }
         while(head != null){
             int i =stack.pop();
             if(head.data==i){
                 ispalin=true;}
             else{
                 ispalin=false;
                 break;
             }
             head = head.next;
         }
         return ispalin;
     }

     void removeduplsorted(){
         Node curr=head;
         while(curr != null){
             Node temp = curr;
             while (temp!=null && temp.data==curr.data){
                 temp = temp.next;
             }
             curr.next=temp;
             curr=curr.next;
         }
     }

     void removeduplunsorted(Node head){
         HashSet<Integer> hs = new HashSet<>();
         Node current =head;
         Node prev= null;
         while (current != null){
             int curval = current.data;
             if(hs.contains(curval)){
                 prev.next=current.next;
             }
             else {
                 hs.add(curval);
                 prev=current;
             }
             current=current.next;
         }
     }

     public static void main(String[] args){
         LinkedList list =new LinkedList();
         //insert 6 .so linkedlist becomes 6->null
         list.append(6);
         list.push(7);
         list.push(1);
         list.append(4);
         list.insertafter(list.head,8);
         System.out.println("list before deleting");
         list.traverse();
         //list.deletelist();
         //list.deletenodebyvalue(1);
         list.deletenodebyposition(3);
         System.out.println("list after deleting");
         list.traverse();
         int m = list.getlength();
         System.out.println(m);
         if (list.search(list.head,8)){
             System.out.println("Yes");
         }
         else
             System.out.println("No");
         list.printNthFromLast(4);
         list.getmid();
         list.countoccur(6);
         list.detectLoop();
     }
 }




