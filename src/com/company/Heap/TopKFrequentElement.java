package com.company.Heap;

import java.util.*;

public class TopKFrequentElement {

    // https://www.youtube.com/watch?v=EBNPu0GgM64

    // nlogk
    public int[] topKFrequentMinHeap(int[] nums, int k) {
        Map<Integer,Integer> countMap = new HashMap<>();

        for(int num:nums){
            countMap.put(num,countMap.getOrDefault(num,0)+1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> countMap.get(a)-countMap.get(b));

        for(int num : countMap.keySet()){
            heap.offer(num);
            if(heap.size()>k){
                heap.poll();
            }
        }

        int result[] = new int[k];
        for(int i = k-1;i>=0;i--){
            result[i] = heap.poll();
        }
        return result;
    }

    //o(n), o(n)
    //uses bucket sort
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer,Integer> countMap = new HashMap<>();

        for(int num:nums){
            countMap.put(num,countMap.getOrDefault(num,0)+1);
        }

        for(int key : countMap.keySet()){
            int frequency = countMap.get(key);
            if(bucket[frequency] == null){
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        int res[] = new int[k];
        int counter = 0;

        for(int pos = bucket.length-1;pos>=0 && counter < k;pos--){
            List<Integer> integers = bucket[pos];
            if(integers != null){
                for(Integer integer: integers){
                    res[counter++] = integer;
                }
            }
        }
        return res;
    }
}
