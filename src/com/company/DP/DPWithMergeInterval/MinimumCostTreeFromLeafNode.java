package com.company.DP.DPWithMergeInterval;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumCostTreeFromLeafNode {

    private int solveRec(int[] arr, Map<String, Integer> maxMap,int left, int right) {
        //leaf node
        if(left == right){
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        for(int i = left;i<right;i++){
            String leftKey = left +String.valueOf(i);
            String rightKey = i+1 +String.valueOf(right);
            ans = Math.min(ans,maxMap.get(leftKey)*maxMap.get(rightKey)+solveRec(arr,maxMap,left,i)+solveRec(arr,maxMap,i+1,right));
        }

        return ans;
    }

    public int mctFromLeafValuesRec(int[] arr) {
        Map<String,Integer> maxMap = new HashMap<>();

        for(int i=0;i<arr.length;i++){
            maxMap.put(i +String.valueOf(i),arr[i]);
            for(int j=i+1;j<arr.length;j++){
                String key = i +String.valueOf(j);
                String prevKey = i +String.valueOf(j-1);
                maxMap.put(key,Math.max(arr[j],maxMap.get(prevKey)));
            }
        }

        return solveRec(arr,maxMap,0,arr.length-1);
    }

    private int solveMem(int[] arr, Map<String, Integer> maxMap,int left, int right, int[][] dp) {
        //leaf node
        if(left == right){
            return 0;
        }

        if(dp[left][right]!=-1){
            return dp[left][right];
        }

        int ans = Integer.MAX_VALUE;

        for(int i = left;i<right;i++){
            String leftKey = left +String.valueOf(i);
            String rightKey = i+1 +String.valueOf(right);
            ans = Math.min(ans,maxMap.get(leftKey)*maxMap.get(rightKey)+solveMem(arr,maxMap,left,i,dp)+solveMem(arr,maxMap,i+1,right,dp));
        }

        return dp[left][right] = ans;
    }

    public int mctFromLeafValuesMem(int[] arr) {
        Map<String,Integer> maxMap = new HashMap<>();
        int[][] dp = new int[arr.length+1][arr.length+1];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }

        for(int i=0;i<arr.length;i++){
            maxMap.put(i +String.valueOf(i),arr[i]);
            for(int j=i+1;j<arr.length;j++){
                String key = i +String.valueOf(j);
                String prevKey = i +String.valueOf(j-1);
                maxMap.put(key,Math.max(arr[j],maxMap.get(prevKey)));
            }
        }

        return solveMem(arr,maxMap,0,arr.length-1,dp);
    }

    private int solveTab(int[] arr, Map<String, Integer> maxMap) {

        int[][] dp = new int[arr.length][arr.length];
        // Initialize base cases
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE); // Initialize with large value
        }

        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = 0; // Single leaf nodes have no cost
        }

        int ans = Integer.MAX_VALUE;

        for(int i = arr.length -1;i>=0;i--){
            for(int j = i+1;j<arr.length;j++){
                for(int k = i;k<j;k++){

                    String leftKey = i + "," + k;
                    String rightKey = (k + 1) + "," + j;

                    int leftMax = maxMap.getOrDefault(leftKey, 0);
                    int rightMax = maxMap.getOrDefault(rightKey, 0);

                    // Compute cost
                    int cost = leftMax * rightMax + dp[i][k] + dp[k + 1][j];

                    // Minimize cost
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][arr.length-1];
    }

    public int mctFromLeafValues(int[] arr) {
        Map<String,Integer> maxMap = new HashMap<>();

        for(int i=0;i<arr.length;i++){
            maxMap.put(i + "," + i,arr[i]);
            for(int j=i+1;j<arr.length;j++){
                String key = i + "," +j;
                String prevKey = i + "," +(j-1);
                maxMap.put(key,Math.max(arr[j],maxMap.get(prevKey)));
            }
        }

        return solveTab(arr,maxMap);
    }


}
