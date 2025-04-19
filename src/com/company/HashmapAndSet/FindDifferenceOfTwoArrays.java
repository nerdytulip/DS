package com.company.HashmapAndSet;

import java.util.*;

public class FindDifferenceOfTwoArrays {
    public List<Integer> getElementsOnlyInFirstArray(int[] nums1, int[] nums2) {
        HashSet<Integer> itemsOnlyInNum1 = new HashSet<>();
        HashSet<Integer> itemsInNum2 = new HashSet<>();

        for(int num:nums2){
            itemsInNum2.add(num);
        }

        for(int num : nums1){
            if(!itemsInNum2.contains(num)){
                itemsOnlyInNum1.add(num);
            }
        }

        return new ArrayList<>(itemsOnlyInNum1);
    }

    public List<List<Integer>> findDifference1(int[] nums1, int[] nums2) {
        return Arrays.asList(getElementsOnlyInFirstArray(nums1,nums2),
                getElementsOnlyInFirstArray(nums2,nums1));
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> setNums1 = new HashSet<>();
        Set<Integer> setNums2 = new HashSet<>();

        for(int num : nums1){
            setNums1.add(num);
        }

        for(int num : nums2){
            setNums2.add(num);
        }

        List<Integer> onlyInNum1 = new ArrayList<>();
        List<Integer> onlyInNum2 = new ArrayList<>();

        for(int num : setNums1){
            if(!setNums2.contains(num)){
                onlyInNum1.add(num);
            }
        }

        for(int num : setNums2){
            if(!setNums1.contains(num)){
                onlyInNum2.add(num);
            }
        }

        result.add(onlyInNum1);
        result.add(onlyInNum2);

        return result;
    }
}
