package com.company.Heap;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    /**
     * https://leetcode.com/problems/find-median-from-data-stream/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=jnj87BSi9Is
     * */

    class MedianFinder {

        PriorityQueue<Integer> leftMaxHeap;
        PriorityQueue<Integer> rightMinHeap;

        public MedianFinder() {
            leftMaxHeap = new PriorityQueue<>((a,b) -> b-a);
            rightMinHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(leftMaxHeap.isEmpty() || num < leftMaxHeap.peek()){
                leftMaxHeap.add(num);
            } else{
                rightMinHeap.add(num);
            }

            //always maintain leftMaxHeap one greater than rightMinHeap or else they would be equal
            if(leftMaxHeap.size() - rightMinHeap.size() > 1){
                rightMinHeap.add(leftMaxHeap.poll());
            }else if(leftMaxHeap.size()<rightMinHeap.size()){
                leftMaxHeap.add(rightMinHeap.poll());
            }
        }

        public double findMedian() {
            if(leftMaxHeap.size() == rightMinHeap.size()){
                double mean = (leftMaxHeap.peek() + rightMinHeap.peek())/2.0;
                return mean;
            } else{
                // we have odd number of elements
                return leftMaxHeap.peek();
            }
        }
    }
}
