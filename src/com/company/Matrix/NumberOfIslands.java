package com.company.Matrix;

public class NumberOfIslands {
    //recursion
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int islandCount = 0;

        for(int i=0;i<grid.length;i++){
            for(int j =0;j<grid[0].length;j++){
                if(grid[i][j] == '1'){
                    dfs(grid,i,j);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    private void dfs(char[][] grid,int i, int j){
        if(i<0 || i>= grid.length || j<0 || j>= grid[0].length || grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0'; // mark cell as visited

        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}
