package com.company.LinkedListQuestions;


import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseLinkedList {
    //TC - o(n) and SC o(n)
    public ListNode reverseListExtraSpace(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();

        while(head!=null){
            stack.push(head.val);
            head = head.next;
        }

        ListNode reversedList = new ListNode(Integer.MIN_VALUE);
        ListNode ptr = reversedList;

        while(!stack.isEmpty()){
            ptr.next = new ListNode(stack.pop());
            ptr = ptr.next;
        }

        return reversedList.next;
    }

    //without using extra space TC - o(n), sc - o(n)
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }

        if(head.next == null){
            return head;
        }

        ListNode preNode = null;
        ListNode currNode = head;

        while(currNode!=null){
            ListNode nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }

        head = preNode;

        return head;
    }
}
