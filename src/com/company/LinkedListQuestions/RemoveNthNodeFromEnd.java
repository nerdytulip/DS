package com.company.LinkedListQuestions;

public class RemoveNthNodeFromEnd {

    /***
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=6gI8OMoac4Q
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode ptr1 = dummy;
        ListNode ptr2 = dummy;

        // Move ptr2 n space ahead
        for(int i=0;i<n;i++){
            ptr2 = ptr2.next;
        }

        // move both now , until the next of ptr2 is null
        while(ptr2.next!=null){
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // we now have to remove the node of next of ptr1
        ptr1.next = ptr1.next.next;

        return dummy.next;
    }
}
