package com.company.Matrix;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExit {

    /**
     * https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/
     * */

    private static class Cell {
        int row;
        int column;
        int distance;

        public Cell(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }
    }
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length, cols = maze[0].length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Mark the entrance as visited since its not a exit.
        int startRow = entrance[0], startCol = entrance[1];
        maze[startRow][startCol] = '+';

        // Start BFS from the entrance, and use a queue `queue` to store all
        // the cells to be visited.
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(startRow, startCol, 0));

        while (!queue.isEmpty()) {
            Cell currCell = queue.poll();

            for(int[] dir: dirs){
                int nextRow = currCell.row + dir[0];
                int nextCol = currCell.column + dir[1];

                // If there exists an unvisited empty neighbor:
                if (0 <= nextRow && nextRow < rows && 0 <= nextCol && nextCol < cols
                        && maze[nextRow][nextCol] == '.') {

                    // If this empty cell is an exit, return distance + 1.
                    if (nextRow == 0 || nextRow == rows - 1 || nextCol == 0 || nextCol == cols - 1){
                        return currCell.distance + 1;
                    }

                    // Otherwise, add this cell to 'queue' and mark it as visited.
                    maze[nextRow][nextCol] = '+';
                    queue.offer(new Cell(nextRow, nextCol, currCell.distance + 1));
                }
            }
        }

        return -1;
    }
}
