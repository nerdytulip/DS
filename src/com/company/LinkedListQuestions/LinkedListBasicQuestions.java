package com.company.LinkedListQuestions;
import java.lang.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

class LinkedListBasicQuestions {
     Node head;
     class Node{
        int data;
        Node next;
        Node(int d){data =d;next=null;}
    }
     void push(int newdata){
        Node newnode = new Node(newdata);
        //make next of new Node to point the head
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

    /*@https://www.geeksforgeeks.org/in-a-linked-list-given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
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
         while(slowptr !=null && fastptr !=null && fastptr.next!=null){
             slowptr=slowptr.next;
             fastptr=fastptr.next.next;
             if(slowptr==fastptr){
                 System.out.println("Found loop");
                 return 1;
             }
         }
         return 0;
     }

     int detectAndremoveLoop(Node node){
        Node slow=node,fast=node;
        while (slow!=null && fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                removeloop(slow,node);
                return 1;
            }
        }
        return 0;
     }

     void removeloop(Node loop,Node head){
        Node ptr1=loop;
        Node ptr2=loop;
        int k=1;
        while (ptr1.next!=ptr2){
            ptr1=ptr1.next;
            k++;
        }
        ptr1=head;

        ptr2=head;
        for (int i=0;i<k;i++){
            ptr2=ptr2.next;
        }

        //Move both pointers at the same pace
         //they will meet at the loop starting node
         while(ptr2!=ptr1){
             ptr1=ptr1.next;
             ptr2=ptr2.next;
         }
         while(ptr2.next!=ptr1){
             ptr2=ptr2.next;
         }

         ptr2.next=null;
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
             head.next=reverse_even_elements(current,null);
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

     //THIS METHOD DOES NOT USE STACK,IT REVERSES THE OTHER HALF OF THE LINKEDLIST AND THEN COMPARE WITH THE STARTING OF THE LIST
    boolean ispalindrome_without_using_stack(Node head){
         Node slow =head;
         Node fast=head;
         while(fast!=null && fast.next!=null){
             fast=fast.next.next;
             slow=slow.next;
         }
         slow=reverse(slow);
         fast=head;
         while (slow!=null){
             if (slow.data!=fast.data)
                 return false;
             slow=slow.next;
             fast=fast.next;
         }
         return true;
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

     /*https://www.geeksforgeeks.org/swap-nodes-in-a-linked-list-without-swapping-data/*/
     void swapNodes_Without_swappingdata(int x,int y){
         if(x==y)
             return;
         Node prevX=null,currX=head;
         while(currX!=null && currX.data!=x){
             prevX=currX;
             currX=currX.next;
         }
         Node prevY=null,currY=head;
         while(currY!=null && currY.data!=x){
             prevY=currY;
             currY=currY.next;
         }
         if (currX==null || currY==null)
             return;
         //if X is not head of linkedlist
         if (prevX!=null)
             prevX.next=currY;
         else
             head=currY;

         //if Y is not head of list
         //then set next of prevY as currX
         if (prevY!=null)
             prevY.next=currX;
         else
             head=currX;

         //swap the next pointers
         Node temp = currX.next;
         currX.next=currY.next;
         currY.next=temp;
     }

     /*https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/*/
    /*here the most significant is the head and least significant is at the end of list*/
    Node addTwoList(Node first,Node second){
        Node res=null;//res is head node of result linkedlist
        Node prev=null;
        Node temp=null;
        int carry=0,sum;
        while(first!=null || second!=null){
            sum=carry+(first!=null?first.data:0)+(second!=null?second.data:0);
            carry=(sum>=10)?1:0;
            sum=sum%10;
            temp=new Node(sum);
            //if this is the first node,then set it as head of resultant list
            if (res==null)
                res=temp;
            else
                prev.next=temp;
            prev=temp;//set prev for next insertion
            if (first!=null)
                first=first.next;
            if (second!=null)
                second=second.next;
        }
        //to tackle if the carry is still left after both addition of complete both the lists
        if (carry>0)
            temp.next=new Node(carry);
        return res;
    }

    Node reverse(Node l){
           if (l==null||l.next==null)
               return l;
           Node prev=null;
           Node curr=l;
           while(curr!=null){
               Node temp=curr.next;
               curr.next=prev;
               prev=curr;
               curr=temp;
           }
           l=prev;
           return l;
    }
    /*https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists-set-3/*/
    //store result inplace and where the most significant digit is head node and tail is the least significant digit
    Node addTwoList_Store_inplace(Node l1,Node l2){
        if (l2==null)return l1;
        if (l1==null)return l2;
        l1=reverse(l1);
        l2=reverse(l2);
        //storing head whose reverse is to be returned after addition
        Node r=l1;
        Node prev=null;
        int c=0,sum;
        while (l1!=null && l2!=null){
            sum=c+l1.data+l2.data;
            l1.data=sum%10;
            c=sum/10;
            prev=l1;
            l1=l1.next;
            l2=l2.next;
        }
        if (l1!=null || l2!=null){
            if (l2!=null)
                prev.next=l2;
            l1=prev.next;
            while(l1!=null){
                sum=c+l1.data;
                l1.data=sum%10;
                c=sum/10;
                prev=l1;
                l1=l1.next;
            }
        }
        if (c>0)
            prev.next=new Node(c);

        return reverse(r);
    }
    boolean borrow;
    int getLength(Node node){
        int size=0;
        while(node!=null){
            node=node.next;
            size++;
        }
        return size;
    }
    Node padZeros(Node sNode,int diff){
        if (sNode == null)
            return null;
        Node zHead=new Node(0);
        diff--;
        Node temp=zHead;
        while ((diff--)!=0){
            temp.next=new Node(0);
            temp=temp.next;
        }
        temp.next=sNode;
        return zHead;
    }
    //https://www.geeksforgeeks.org/subtract-two-numbers-represented-as-linked-lists/
    Node subtractList(Node l1,Node l2){
        if(l1==null && l2==null)
            return null;
        int len1=getLength(l1);
        int len2=getLength(l2);
        Node lNode=null,sNode=null;
        Node temp1=l1;
        Node temp2=l2;
        if (len1!=len2){
            lNode=len1>len2?l1:l2;
            sNode=len1>len2?l2:l1;
            sNode=padZeros(sNode,Math.abs(len1-len2));
        }
        else{
            while(l1!=null && l2!=null){
                if (l1.data!=l2.data){
                    lNode=l1.data>l2.data?temp1:temp2;
                    sNode=l1.data>l2.data?temp2:temp1;
                    break;
                }
                l1=l1.next;
                l2=l2.next;
            }
        }
        borrow=false;
        return subtractListHelper(lNode,sNode);
    }
    Node subtractListHelper(Node l1,Node l2){
        if (l1==null && l2==null && borrow==false)
            return null;
        Node prev=subtractListHelper((l1 !=null)? l1.next:null,(l2 !=null)? l2.next:null);

        int d1=l1.data;
        int d2=l2.data;
        int sub=0;

        if (borrow){
            d1--;
            borrow=false;
        }

        if (d1<d2){
            borrow=true;
            d1=d1+10;
        }

        sub=d1-d2;
        Node curr=new Node(sub);
        curr.next=prev;
        return curr;
    }
    static void findNext(char ar[],int n){
         int i;
         for(i=n-1;i>0;i--){
             if (ar[i]>ar[i-1])
                 break;
         }
         if (i==0){
             System.out.println("not possible");
         }
         else{
             int x=ar[i-1],min=i;
             for (int j=i+1;j<n;j++){
                 if (ar[j]>x && ar[j]<ar[min]){
                     min=j;
                 }
             }
             swap(ar,i-1,min);
             Arrays.sort(ar,i,n);
             System.out.print("Next number with same"+"set of digits is");
             for (i=0;i<n;i++)
                 System.out.print(ar[i]);
         }
     }
     static void swap(char ar[],int i,int j){
         char temp=ar[i];
         ar[i]=ar[j];
         ar[j]=temp;
     }
     void printList(Node head){
        Node temp=head;
        while (temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
     }
     public static void main(String[] args){
         LinkedListBasicQuestions list =new LinkedListBasicQuestions();
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
         LinkedListBasicQuestions l1=new LinkedListBasicQuestions();
         LinkedListBasicQuestions l2=new LinkedListBasicQuestions();
         l1.push(1);
         l1.push(0);
         l1.push(0);

         l2.push(1);
         Node result=list.subtractList(l1.head,l2.head);
         list.printList(result);
     }
 }




