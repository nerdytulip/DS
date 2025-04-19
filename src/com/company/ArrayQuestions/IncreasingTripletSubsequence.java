package com.company.ArrayQuestions;

public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;

        for(int num:nums){
            if(num<=small){
                small = num;
            }else if(num<=mid){
                mid = num;
            }else{
                return true;
            }
        }

        return false;
    }
}
