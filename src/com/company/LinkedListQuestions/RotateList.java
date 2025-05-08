package com.company.LinkedListQuestions;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode tail = head;

        while(tail.next!=null){
            tail = tail.next;
            length++;
        }

        k = k%length;
        // if length == k , it comes to be original list
        // if k > length , then that means after length == k  we will end up with original list and then actual rotation will be k - length times
        // if k == 0 , k is multiple of length and we do not need to do any rotations
        if(k == 0){
            return head;
        }

        // move to the pivot and perform rotate;
        ListNode curr = head;

        for(int i =0;i<length-k-1;i++){
            curr = curr.next;
        }

        ListNode newHead = curr.next;
        curr.next = null;
        tail.next = head;

        return newHead;
    }
}
