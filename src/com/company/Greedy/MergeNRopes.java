package com.company.Greedy;

import java.util.PriorityQueue;

public class MergeNRopes {
    public static int minCost(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int a : arr){
            pq.add(a);
        }

        int cost = 0;
        while(pq.size()>1){
            int first = pq.poll();
            int second = pq.poll();

            int mergedLength = first+second;

            cost = cost + mergedLength;

            pq.add(mergedLength);
        }

        return cost;
    }
}
