package com.company.LinkedListQuestions;

public class ReorderList {
    /**
     * https://leetcode.com/problems/reorder-list/
     *
     * Steps :
     * 1) Find the midpoint
     * 2) reverse second half
     * 3) join them again
     * */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        // find the middle of the list
        // slow - fast pointer (floyd warshall)

        ListNode slow = head;
        ListNode fast = head;

        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the half after middle
        ListNode preMiddle = slow;
        ListNode preCurrent = slow.next;
        while (preCurrent.next!=null){
            ListNode current = preCurrent.next;
            preCurrent.next = current.next;
            current.next = preMiddle.next;
            preMiddle.next = current;
        }

        // start reordering
        ListNode p1 = head;
        ListNode p2 = preMiddle.next;
        while(p1 != preMiddle){
           preMiddle.next = p2.next;
           p2.next = p1.next;
           p1.next = p2;
           p1 = p2.next;
           p2 = preMiddle.next;
        }
    }
}
