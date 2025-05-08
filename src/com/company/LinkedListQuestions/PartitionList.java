package com.company.LinkedListQuestions;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode();
        ListNode right = new ListNode();

        ListNode lTail = left;
        ListNode rTail = right;

        while(head!=null){
            if(head.val<x){
                lTail.next = head;
                lTail = lTail.next;
            }else{
                rTail.next = head;
                rTail = rTail.next;
            }
            head = head.next;
        }

        // we do right.next , because right itself is dummy node, so right.next is the actual node form list
        lTail.next = right.next;
        rTail.next = null;

        return left.next;
    }
}
