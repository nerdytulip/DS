package com.company.ArrayQuestions;

import java.util.HashMap;

public class ArrayLeetCodeEasy {

    //o(n^2)
    public int[] twoSum1(int[] nums, int target) {

        for(int i=0;i<nums.length;i++){
            int sum = nums[i];
            for(int j = i+1;j<nums.length;i++){
                sum += sum + nums[j];
                if(sum == target){
                    return new int[]{i,j};
                }
            }
        }

        return new int[] {};
    }

    public int[] twoSum(int[] nums, int target) {
        //key - value from array , value = indec which has value
        HashMap<Integer,Integer> valueMap = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            valueMap.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if(valueMap.containsKey(complement) && valueMap.get(complement)!=i){
                return new int[]{i, valueMap.get(complement)};
            }
        }
        return null;
    }

}
