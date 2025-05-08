package com.company.LinkedListQuestions;

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode curr = head;
        ListNode newHead = null;
        ListNode tail = null;

        while (curr != null) {
            // Check if there are at least k nodes left
            ListNode check = curr;
            int count = 0;
            while (check != null && count < k) {
                check = check.next;
                count++;
            }

            if (count < k) {
                // Less than k nodes left, don't reverse, just connect
                if (tail != null) {
                    tail.next = curr;
                }
                break;
            }

            ListNode groupHead = curr;
            ListNode prev = null;
            ListNode nextNode = null;
            count = 0;

            // Reverse the nodes in the current group
            while (curr != null && count < k) {
                nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
                count++;
            }

            // If newHead is null, set it to the
            // last node of the first group
            if (newHead == null) {
                newHead = prev;
            }

            // Connect the previous group to the
            // current reversed group
            if (tail != null) {
                tail.next = prev;
            }

            // Move tail to the end of the
            // reversed group
            tail = groupHead;
        }

        return newHead;
    }

    public static void main(String[] args) {
        ReverseKGroup kGroup= new ReverseKGroup();

        // Creating a sample singly linked list:
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        head = kGroup.reverseKGroup(head, 3);
    }
}
