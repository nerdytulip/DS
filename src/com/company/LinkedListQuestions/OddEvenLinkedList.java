package com.company.LinkedListQuestions;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if(head == null){
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;

        ListNode evenHead = even;

        while(even!=null && even.next!=null){
            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }
}
