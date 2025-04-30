package com.company.Greedy;

import java.util.Arrays;

public class Candy {
    /**
     * https://leetcode.com/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=1IzCRCcK17A&t=222s
     * */

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] result = new int[n];
        Arrays.fill(result,1);

        // first pass - L to R , if an element is greater than the element on left increment the count of candies for it
        for(int i=1;i<n;i++){
            if(ratings[i-1]<ratings[i]){
                result[i] = result[i-1]+1;
            }
        }

        // second pass - L to R , if an element is greater than the element on right increment the count of candies for it
        for(int i=n-2;i>=0;i--){
            if(ratings[i]>ratings[i+1]){
                result[i] = Math.max(result[i],result[i+1]+1);
            }
        }

        int totalSum = 0;
        for(int r:result){
            totalSum+=r;
        }

        return totalSum;
    }
}
