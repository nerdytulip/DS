package com.company.TwoPointer;

import java.util.Arrays;
import java.util.HashMap;

public class MaxNumberOfKSumPairs {
    public int maxOperationsBF(int[] nums, int k) {
        int maxOperations = 0;

        for(int i=0;i < nums.length;i++){
            for(int j = i+1;j< nums.length;j++){
                if(nums[i]!=0 && nums[j]!=0 && (nums[i] + nums[j] == k)){
                    maxOperations++;
                    nums[i] = 0;
                    nums[j] = 0;
                }
            }
        }

        return maxOperations;
    }

    public int maxOperations_Map(int[] nums, int k) {
        int maxOperations = 0;

        HashMap<Integer,Integer> countMap = new HashMap<>();

        for(int num : nums){
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }

        for(int i =0;i<nums.length;i++){
            int complement = k - nums[i];
            if(countMap.getOrDefault(nums[i],0)>0 && countMap.getOrDefault(complement,0)>0 ){
                if(nums[i] == complement && countMap.get(nums[i]) < 2){
                    continue;
                }
                countMap.put(nums[i], countMap.get(nums[i])-1);
                countMap.put(complement, countMap.get(complement)-1);
                maxOperations++;
            }
        }

        return maxOperations;
    }

    public int maxOperations(int[] nums, int k) {
        int maxOperations = 0;
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;

        while(i<j){
            int sum = nums[i]+nums[j];
            if(sum == k){
                i++;
                j--;
                maxOperations++;
            } else if(sum < k){
                i++;
            }else{
                j--;
            }
        }

        return maxOperations;
    }
}
