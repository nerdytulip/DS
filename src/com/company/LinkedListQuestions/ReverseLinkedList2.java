package com.company.LinkedListQuestions;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //markers for currNode and for node before reversing
        ListNode leftPre = dummy;
        ListNode currNode = head;

        for(int i =0;i<left-1;i++){
            leftPre = leftPre.next;
            currNode = currNode.next;
        }

        //marker to ndoe where we start reversing
        ListNode subListHead = currNode;

        ListNode preNode = null;
        for(int i=0;i<=right-left;i++){
            ListNode nextNode = currNode.next;
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }

        leftPre.next = preNode;
        subListHead.next = currNode;

        return dummy.next;
    }
}
