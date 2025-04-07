package com.company.Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatInAMazeProblem {
    public List<String> findPath(int[][] maze,int n){
        List<String> paths = new ArrayList<>();

        if(maze[0][0] == 0){
            return paths;
        }

        int srcx = 0;
        int srcy = 0;
        int[][] visited = new int[maze.length][maze[0].length]; // byd default all values will be 0
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                visited[i][j] = 0;
            }
        }
        StringBuilder currentPath = new StringBuilder();

        solvePaths(maze,n,paths,srcx,srcy,visited,currentPath);
        Collections.sort(paths);

        return paths;
    }

    private void solvePaths(int[][] maze, int n, List<String> paths, int x, int y, int[][] visited, StringBuilder currentPath) {

        //you have visted x,y here

        //base case : destination
        if(x == n-1 && y == n-1 ){
            paths.add(currentPath.toString());
            return;
        }

        //marking x,y visited
        visited[x][y] = 1;

        // 4 choices D,L,R,U
        //down
        int newx = x+1;
        int newy = y;
        if(isSafe(newx,newy,n,visited,maze)){
            currentPath.append("D");
            solvePaths(maze,n,paths,newx,newy,visited,currentPath);
            //backtrack
            currentPath.deleteCharAt(currentPath.length()-1);
        }

        //left
        newx = x;
        newy = y-1;
        if(isSafe(newx,newy,n,visited,maze)){
            currentPath.append("L");
            solvePaths(maze,n,paths,newx,newy,visited,currentPath);
            //backtrack
            currentPath.deleteCharAt(currentPath.length()-1);
        }

        //right
        newx = x;
        newy = y+1;
        if(isSafe(newx,newy,n,visited,maze)){
            currentPath.append("R");
            solvePaths(maze,n,paths,newx,newy,visited,currentPath);
            //backtrack
            currentPath.deleteCharAt(currentPath.length()-1);
        }

        //up
        newx = x-1;
        newy = y;
        if(isSafe(newx,newy,n,visited,maze)){
            currentPath.append("U");
            solvePaths(maze,n,paths,newx,newy,visited,currentPath);
            //backtrack
            currentPath.deleteCharAt(currentPath.length()-1);
        }

        //marking x,y unvisited because you are returning from this function - back track
        visited[x][y] = 0;
    }

    private boolean isSafe(int x, int y, int n, int[][] visited, int[][] maze) {

        // if the new values are in matrix
        if((x>=0 && x<n) && (y>=0 && y<n) && visited[x][y] == 0 && maze[x][y] ==1){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        RatInAMazeProblem mazeProblem = new RatInAMazeProblem();
        int[][] maze = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 } };

        int n = maze.length;

        List<String> paths = mazeProblem.findPath(maze, n);
        System.out.println("possible paths" + paths);
    }

}
