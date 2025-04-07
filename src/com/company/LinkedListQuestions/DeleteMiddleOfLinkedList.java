package com.company.LinkedListQuestions;

public class DeleteMiddleOfLinkedList {
    public ListNode deleteMiddle(ListNode head) {

        // If there's 0 or 1 node, return null since the middle node is the only node.
        if(head == null || head.next == null) {
            return null;
        }

        ListNode curr = head;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while(fastPtr!=null && fastPtr.next!=null){
            curr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        curr.next = slowPtr.next;
        slowPtr.next = null;

        return head;
    }
}
