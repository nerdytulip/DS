package com.company.DivideConquer;

import com.company.LinkedListQuestions.ListNode;

public class SortList {
    /**
     * https://leetcode.com/problems/sort-list/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=pNTc1bM1z-4
     * */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            temp = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        // head is head of first list
        // temp is tail of first list
        // slow is head of next list
        // fast is tail of next list

        temp.next = null;

        ListNode leftSide = sortList(head);
        ListNode rightSide = sortList(slow);

        return merge(leftSide,rightSide);
    }

    private ListNode merge(ListNode leftSide, ListNode rightSide) {
        ListNode sortedTemp = new ListNode(0);
        ListNode currNode = sortedTemp;

        while(leftSide!=null && rightSide!=null){
            if(leftSide.val < rightSide.val){
                currNode.next = leftSide;
                leftSide = leftSide.next;
            } else{
                currNode.next = rightSide;
                rightSide = rightSide.next;
            }
            currNode = currNode.next;
        }

        // if either are shorter
        if (leftSide != null) {
            currNode.next = leftSide;
        }

        if (rightSide != null) {
            currNode.next = rightSide;
        }

        return sortedTemp.next;
    }
}
