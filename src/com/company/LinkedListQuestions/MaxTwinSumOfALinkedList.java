package com.company.LinkedListQuestions;

public class MaxTwinSumOfALinkedList {
    public int pairSum(ListNode head) {
        //find middle of linkedlist
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while(fastPtr!=null && fastPtr.next!=null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        //reverse link
        ListNode nextNode, prev = null;

        while(slowPtr!=null){
            nextNode = slowPtr.next;
            slowPtr.next  = prev;
            prev  = slowPtr;
            slowPtr = nextNode;
        }

        int maxVal = Integer.MIN_VALUE;
        while(prev!=null){
            maxVal = Math.max(maxVal,prev.val+head.val);
            prev = prev.next;
            head = head.next;
        }

        return maxVal;
    }
}
