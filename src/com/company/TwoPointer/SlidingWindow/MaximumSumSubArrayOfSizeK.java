package com.company.TwoPointer.SlidingWindow;

public class MaximumSumSubArrayOfSizeK {
    //Fixed sliding window
    public int maxSumSubArrayOfSizeK(int arr[], int k)
    {
        int i=0,j=0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        while(j<arr.length){
            sum+=arr[j];
            if(j-i+1<k){
                j++;
            }else if(j-i+1 == k){
                maxSum = Math.max(sum,maxSum);
                sum -=arr[i];
                i++;
                j++;
            }
        }
        return maxSum;
    }

    public static void main(String[] args){

        MaximumSumSubArrayOfSizeK maximumSumSubArrayOfSizeK = new MaximumSumSubArrayOfSizeK();
//        int lengthOfLongestSubstring = slidingWindowTechnique.lengthOfLongestSubstring("eeydgwdykpv");
//        System.out.println(lengthOfLongestSubstring);

//        int longestKSubstr = slidingWindowTechnique.longestKSubstr("aabacbebebe", 3);
//        System.out.println(longestKSubstr);

        int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        System.out.println(maximumSumSubArrayOfSizeK.maxSumSubArrayOfSizeK(arr, k));
    }
}
