package com.company.LinkedListQuestions;

import java.util.PriorityQueue;

 // https://www.youtube.com/watch?v=ptYUCjfNhJY&pp=ygUibWVyZ2UgayBzb3J0ZWQgbGlzdHMgamF2YSBtaW4gaGVhcA%3D%3D
 // https://www.youtube.com/watch?v=ptYUCjfNhJY
 // https://www.youtube.com/watch?v=IgZ4EqbBi1Q

public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        int length = lists.length;
        if(lists==null || length ==0){
            return null;
        }
        if(length == 1) return lists[0];

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.val - b.val);

        // put heads of lists inside priority queue
        for(int i = 0; i< length; i++){
            if(lists[i]!=null){
                minHeap.add(lists[i]);
            }
        }

        //remove minimum and add to list
        while(!minHeap.isEmpty()){
            ListNode min = minHeap.remove();
            if(min.next!=null) minHeap.add(min.next);

            current.next = min;
            current = current.next;
        }

        return dummy.next;
    }

    // Divide and Conquer
    // O(nlogk) https://www.youtube.com/watch?v=qvQ2fOHQ3rw
    public ListNode mergeKListsOpt(ListNode[] lists) {
        int interval = 1; // for first we would merge 0,1 - 2,3 etc , for second we would merge 0 and 2 , then in next we would merge 0 and 4 , so we are doubling the interval everytime

        MergeTwoSortedList mergeTwoSortedList = new MergeTwoSortedList();
        int k = lists.length;
        while(interval<k){
            for(int i = 0; i+interval< k; i += interval*2){
                lists[i] = mergeTwoSortedList.mergeTwoLists(lists[i],lists[i+interval]);
            }
            interval = interval*2;
        }
        return lists.length == 0 ? null : lists[0];
    }

    // Helper method to print the list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        MergeKSortedList merger = new MergeKSortedList();

        // Example input: [[1,4,5],[1,3,4],[2,6]]
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode l4 = new ListNode(0);
        l4.next = new ListNode(2);
        l4.next.next = new ListNode(6);

        // Creating l5: A sorted list like: 3 -> 4 -> 7 -> 8
        ListNode l5 = new ListNode(3);
        l5.next = new ListNode(4);
        l5.next.next = new ListNode(7);
        l5.next.next.next = new ListNode(8);

        // Creating l6: A sorted list like: 1 -> 5 -> 9 -> 10
        ListNode l6 = new ListNode(1);
        l6.next = new ListNode(5);
        l6.next.next = new ListNode(9);
        l6.next.next.next = new ListNode(10);

        ListNode[] lists = new ListNode[]{l1, l2, l3, l4, l5, l6};

        ListNode mergedHead = merger.mergeKListsOpt(lists);

        System.out.print("Merged List: ");
        printList(mergedHead);

    }
}
