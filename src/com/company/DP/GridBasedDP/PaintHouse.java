package com.company.DP.GridBasedDP;

public class PaintHouse {

    //https://leetcode.com/problems/paint-house/
    public int minCost(int[][] costs) {
        for(int i=1;i<costs.length;i++){
            costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0],costs[i-1][1]);
        }

        return Math.min(costs[costs.length - 1][0],
                Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
    }

    //https://leetcode.com/problems/paint-house-ii/
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;

        int n = costs.length;
        int k = costs[0].length;

        for(int i=1;i<n;i++){
            for(int c=0;c<k;c++){
                int minCost = Integer.MAX_VALUE;
                for (int prevC = 0; prevC < k; prevC++) {
                    if (prevC != c) { // Ensure adjacent houses have different colors
                        minCost = Math.min(minCost, costs[i - 1][prevC]);
                    }
                }

                costs[i][c] += minCost;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int c = 0; c < k; c++) {
            result = Math.min(result, costs[n - 1][c]);
        }
        return result;
    }
}
