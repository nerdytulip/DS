package com.company.DP.ZeroOneKnapsack;

import java.util.Arrays;

public class ReducingDishes {

    //https://leetcode.com/problems/reducing-dishes/
    class Solution {
        int solveRec(int[] satisfaction, int index, int time) {
            // Base case
            if (index == satisfaction.length) {
                return 0;
            }

            // incl
            int include = satisfaction[index] * (time + 1) + solveRec(satisfaction, index + 1, time + 1);

            // excl
            int exclude = 0 + solveRec(satisfaction, index + 1, time);

            return Math.max(include, exclude);
        }

        public int maxSatisfactionRec(int[] satisfaction) {
            Arrays.sort(satisfaction);
            return solveRec(satisfaction, 0, 0);
        }

        int solveMem(int[] satisfaction, int index, int time, int[][] memo) {
            // Base case
            if (index == satisfaction.length) {
                return 0;
            }

            if (memo[index][time] != -1) {
                return memo[index][time];
            }

            // incl
            int include = satisfaction[index] * (time + 1) + solveMem(satisfaction, index + 1, time + 1, memo);

            // excl
            int exclude = 0 + solveMem(satisfaction, index + 1, time, memo);

            memo[index][time] = Math.max(include, exclude);
            return memo[index][time];
        }

        public int maxSatisfactionMem(int[] satisfaction) {
            Arrays.sort(satisfaction);
            int n = satisfaction.length;
            int[][] memo = new int[n + 1][n + 1];
            for (int rows[] : memo) {
                Arrays.fill(rows, -1);
            }
            return solveMem(satisfaction, 0, 0, memo);
        }

        int solveTab(int[] satisfaction) {

            Arrays.sort(satisfaction);

            int n = satisfaction.length;
            int[][] memo = new int[n + 1][n + 1];
            for (int rows[] : memo) {
                Arrays.fill(rows, 0);
            }

            for (int index = n - 1; index >= 0; index--) {
                for (int time = index; time >= 0; time--) {
                    // incl
                    int include = satisfaction[index] * (time + 1) + memo[index + 1][time + 1];

                    // excl
                    int exclude = 0 + memo[index + 1][time];

                    memo[index][time] = Math.max(include, exclude);
                }
            }

            return memo[0][0];
        }

        int solveSO(int[] satisfaction) {

            Arrays.sort(satisfaction);

            int n = satisfaction.length;
            int[] curr = new int[n + 1];
            Arrays.fill(curr, 0);
            int[] next = new int[n + 1];
            Arrays.fill(next, 0);

            for (int index = n - 1; index >= 0; index--) {
                for (int time = index; time >= 0; time--) {
                    // incl
                    int include = satisfaction[index] * (time + 1) + next[time + 1];

                    // excl
                    int exclude = 0 + next[time];

                    curr[time] = Math.max(include, exclude);
                }

                int[] temp = next;
                next = curr;
                curr = temp;
            }

            return next[0];
        }

        public int maxSatisfaction(int[] satisfaction) {
            return solveSO(satisfaction);
        }
    }
}
