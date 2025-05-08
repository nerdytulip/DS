package com.company.HashmapAndSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int idx = 0;
        for(int num : nums){
            if(map.containsKey(num) && Math.abs(idx - map.get(num)) <= k){
                return true;
            }else{
                map.put(num,idx);
            }
            idx++;
        }

        return false;
    }

    public boolean containsNearbyDuplicate_UsingHashSet(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int i=0;i< nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if(set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
