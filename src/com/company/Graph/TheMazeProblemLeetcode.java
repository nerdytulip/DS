package com.company.Graph;

import java.util.*;
import java.util.LinkedList;

public class TheMazeProblemLeetcode {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        //0 = open
        //1 = wall
        boolean[][] visited = new boolean[maze.length][maze[0].length];//by default all the values will be false
        visited[start[0]][start[1]] = true;
        Queue<int[]> q = new LinkedList<>();
        // D L R U
        int[][] dirs = new int[][]{{1,0},{0,-1},{0,1},{-1,0}};
        q.add(start);

        while(!q.isEmpty()){
            int curr[] = q.poll();

            if(curr[0] == destination[0] && curr[1] == destination[1]){
                return true;
            }

            for(int[] dir:dirs){
                int x = dir[0]+curr[0];
                int y = dir[1]+curr[1];
                while (x>=0 && y>=0 && x< maze.length && y< maze[0].length && maze[x][y] == 0){
                    x +=dir[0];
                    y +=dir[1];
                }

                //if we do no do this the pointer will go to position with 1 in maze
                x -=dir[0];
                y -=dir[1];

                if(!visited[x][y]){
                    q.offer(new int[]{x,y});
                    visited[x][y] = true;
                }


            }
        }

        return false;

    }


}
