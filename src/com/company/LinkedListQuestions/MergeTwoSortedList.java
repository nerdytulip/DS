package com.company.LinkedListQuestions;

public class MergeTwoSortedList {

    // https://www.youtube.com/watch?v=0ougDTvVOFI
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // dummy node to start
        ListNode returnNode = new ListNode(Integer.MIN_VALUE);

        ListNode headNode = returnNode;

        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                returnNode.next = list1;
                list1 = list1.next;
            }else{
                returnNode.next = list2;
                list2 = list2.next;
            }

            returnNode = returnNode.next;
        }

        //append remaining list
        if(list1 == null){
            returnNode.next = list2;
        }else if(list2 == null){
            returnNode.next = list1;
        }

        return headNode.next;
    }
}
