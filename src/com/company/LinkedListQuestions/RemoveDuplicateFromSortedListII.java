package com.company.LinkedListQuestions;

public class RemoveDuplicateFromSortedListII {

    /**
     * https://www.youtube.com/watch?v=eFPFwwojxGU
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        while(curr!=null){
            //if current node is a duplicate
            if(curr.next!=null && curr.val == curr.next.val){

                // move to last duplicated node
                while(curr.next!=null && curr.val == curr.next.val){
                    curr = curr.next;
                }

                // skip the duplicated nodes
                prev.next = curr.next;
            } else{
                // we have found unique node
                prev = prev.next;
            }

            curr = curr.next;
        }

        return dummy.next;
    }
}
