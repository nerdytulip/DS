package com.company.Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {

    /***
     * https://www.youtube.com/watch?v=26IT3FYm5h8
     * https://leetcode.com/problems/snakes-and-ladders/description/?envType=study-plan-v2&envId=top-interview-150
     */

    private int n;

    private int[] getCoord(int s) {
        int row = n - 1 - (s - 1) / n; // row from bottom
        int col = (s - 1) % n;

        // right to left
        if ((n % 2 == 1 && row % 2 == 1) || (n % 2 == 0 && row % 2 == 0)) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }

    public int snakesAndLadders(int[][] board) {
        n = board.length;
        int steps = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(1);
        visited[n - 1][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int x = queue.poll();

                if (x == n * n) {
                    return steps;
                }

                for (int k = 1; k <= 6; k++) {
                    if (x + k > n * n) break;

                    int[] coord = getCoord(x + k);
                    int r = coord[0];
                    int c = coord[1];

                    if (visited[r][c]) continue;

                    visited[r][c] = true;
                    if (board[r][c] == -1) {
                        queue.offer(x + k);
                    } else {
                        queue.offer(board[r][c]);
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}
