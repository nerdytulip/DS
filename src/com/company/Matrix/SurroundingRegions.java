package com.company.Matrix;

public class SurroundingRegions {
    public void solve(char[][] board) {
        //step 1 - birder and connected 'O' - mark as V

        int m = board.length;
        int n = board[0].length;

        for(int j =0;j<n;j++){
            if(board[0][j] == 'O') floodFill(0,j,m,n,board);
            if(board[m-1][j] == 'O') floodFill(m-1,j,m,n,board);
        }

        for(int i =0;i<m;i++){
          if(board[i][0] == 'O') floodFill(i,0,m,n,board);
          if(board[i][n-1] == 'O') floodFill(i,n-1,m,n,board);
        }

        //step 2 - left over 'O' convert them to X
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }

        //step 3 - flip V to O
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == 'V') board[i][j] = 'O';
            }
        }
    }

    //dfs
    private void floodFill(int i, int j, int m, int n, char[][] board) {
        board[i][j] = 'V';

        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

        for(int k =0;k<4;k++){
            int newR = i + dir[k][0];
            int newC = j + dir[k][1];

            if(isSafe(newR, newC, m, n) && board[newR][newC] == 'O'){
                floodFill(newR,newC,m,n,board);
            }
        }
    }

    private static boolean isSafe(int newR, int newC, int m, int n) {
        return newR >= 0 && newR < m && newC >= 0 && newC < n;
    }
}
