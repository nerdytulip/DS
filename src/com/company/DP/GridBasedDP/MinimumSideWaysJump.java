package com.company.DP.GridBasedDP;

import java.util.Arrays;

public class MinimumSideWaysJump {
    class Solution {
        int solveRec(int[] obstacles, int currlane, int currpos) {
            // Base case
            if (currpos == obstacles.length - 1) {
                return 0;
            }

            // if there is obstacle at next position in the same lane
            if (obstacles[currpos + 1] != currlane) {
                // go straight, so we will call rec function on next lane
                return solveRec(obstacles, currlane, currpos + 1);
            } else {
                // sideways jump
                int ans = Integer.MAX_VALUE;
                for (int i = 1; i <= 3; i++) {
                    if (currlane != i && obstacles[currpos] != i) {
                        ans = Math.min(ans, 1 + solveRec(obstacles, i, currpos));
                    }
                }
                return ans;
            }
        }

        public int minSideJumpsRec(int[] obstacles) {
            return solveRec(obstacles, 2, 0);
        }

        // top-down
        int solveMem(int[] obstacles, int currlane, int currpos, int[][] memo) {
            // Base case
            if (currpos == obstacles.length - 1) {
                return 0;
            }

            if (memo[currlane][currpos] != -1) {
                return memo[currlane][currpos];
            }

            // if there is obstacle at next position in the same lane
            if (obstacles[currpos + 1] != currlane) {
                // go straight, so we will call rec function on next lane
                return solveMem(obstacles, currlane, currpos + 1, memo);
            } else {
                // sideways jump
                int ans = Integer.MAX_VALUE;
                for (int i = 1; i <= 3; i++) {
                    if (currlane != i && obstacles[currpos] != i) {
                        ans = Math.min(ans, 1 + solveMem(obstacles, i, currpos, memo));
                    }
                }
                memo[currlane][currpos] = ans;
                return memo[currlane][currpos];
            }
        }

        public int minSideJumpsMem(int[] obstacles) {
            //size 4 so that we can consider index = 1 as lane 1
            int[][] memo = new int[4][obstacles.length];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return solveMem(obstacles, 2, 0, memo);
        }

        // top-down
        int solveTab(int[] obstacles) {
            int n = obstacles.length - 1;
            //size 4 so that we can consider index = 1 as lane 1
            int[][] memo = new int[4][obstacles.length];
            for (int[] row : memo) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            memo[0][n] = 0;
            memo[1][n] = 0;
            memo[2][n] = 0;
            memo[3][n] = 0;

            for (int currpos = n - 1; currpos >= 0; currpos--) {
                for (int currlane = 1; currlane <= 3; currlane++) {
                    if (obstacles[currpos + 1] != currlane) {
                        // go straight, so we will call rec function on next lane
                        memo[currlane][currpos] = memo[currlane][currpos + 1];
                    } else {
                        // sideways jump
                        int ans = Integer.MAX_VALUE;
                        for (int i = 1; i <= 3; i++) {
                            if (currlane != i && obstacles[currpos] != i) {
                                //currpos +1 , because in top-down , memo[i][currpos] hasn't been populated yet
                                ans = Math.min(ans, 1 +  memo[i][currpos+1]);
                            }
                        }
                        memo[currlane][currpos] = ans;
                    }
                }
            }

            return Math.min(memo[2][0],Math.min(memo[1][0]+1,memo[3][0]+1));
        }

        public int minSideJumps(int[] obstacles) {

            return solveSO(obstacles);
        }

        // top-down
        int solveSO(int[] obstacles) {
            int n = obstacles.length - 1;
            int[] curr = new int[4];
            Arrays.fill(curr, Integer.MAX_VALUE);

            int[] next = new int[4];
            Arrays.fill(next, Integer.MAX_VALUE);


            next[0] = 0;
            next[1] = 0;
            next[2] = 0;
            next[3] = 0;

            for (int currpos = n - 1; currpos >= 0; currpos--) {
                for (int currlane = 1; currlane <= 3; currlane++) {
                    if (obstacles[currpos + 1] != currlane) {
                        // go straight, so we will call rec function on next lane
                        curr[currlane] = next[currlane];
                    } else {
                        // sideways jump
                        int ans = Integer.MAX_VALUE;
                        for (int i = 1; i <= 3; i++) {
                            if (currlane != i && obstacles[currpos] != i) {
                                //currpos +1 , because in top-down , memo[i][currpos] hasn't been populated yet
                                ans = Math.min(ans, 1 +  next[i]);
                            }
                        }
                        curr[currlane]= ans;
                    }
                }
                next = curr;
            }

            return Math.min(next[2],Math.min(next[1]+1,next[3]+1));
        }
    }
}
