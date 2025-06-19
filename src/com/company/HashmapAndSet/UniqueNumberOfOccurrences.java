package com.company.HashmapAndSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UniqueNumberOfOccurrences {
    /**
     * https://leetcode.com/problems/unique-number-of-occurrences/description/
     * */
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int num : arr){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }

        Set<Integer> occurrenceSet = new HashSet<>(map.values());

        return map.size() == occurrenceSet.size();
    }
}
