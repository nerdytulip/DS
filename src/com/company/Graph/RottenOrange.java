package com.company.Graph;

import java.util.Deque;
import java.util.LinkedList;

public class RottenOrange {
    // multisource bfs
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int freshOranges = 0; // Counter for fresh oranges
        Deque<int[]> queue = new LinkedList<>(); // Queue for rotten oranges' positions

        // Preprocess the grid, enqueue all rotten oranges and count the fresh ones
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 2) { // If orange is rotten
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) { // If orange is fresh
                    freshOranges++;
                }
            }
        }

        int minutesElapsed = 0; // Time counter for rotting process
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty() && freshOranges > 0) {
            minutesElapsed++;
            int queueSize = queue.size();

            for(int i = 0; i < queueSize; i++){
                int[] position = queue.poll();
                for(int[] dir: dirs){
                    int x = position[0] + dir[0];
                    int y = position[1] + dir[1];
                    // If adjacent cell is within bounds and has a fresh orange
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2; // Rot the fresh orange
                        freshOranges--; // Decrement the fresh orange count
                        queue.offer(new int[] {x, y}); // Enqueue the new rotten orange's position
                    }
                }
            }
        }

        // If there are still fresh oranges left, return -1, otherwise return elapsed time
        return freshOranges > 0 ? -1 : minutesElapsed;
    }
}
