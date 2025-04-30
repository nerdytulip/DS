package com.company.Graph;

import java.util.HashMap;

public class GameOfLife {

    public int countNeighbours(int[][] board, int r, int c){
        int count = 0;
        int rows = board.length;
        int columns = board[0].length;

        // starting to -1 and going till 1 , because at any point in time we need , r-1,r,r+1,c-1,c,c+1
        for(int dr=-1;dr<=1;dr++){
            for(int dc=-1;dc<=1;dc++){
                //skip the current cell , like we are not counting the board[r][c] itself in neighbours
                if(dr==0 && dc==0){
                    continue;
                }

                int newR = r + dr;
                int newC = c + dc;

                //ensures only calculates valid
                if(newR>=0 && newR< rows && newC>=0 && newC<columns){
                    count+=board[newR][newC];
                }
            }
        }

        return count;
    }

    public void gameOfLife(int[][] board) {
        // live  = 1 , dead = 0
        HashMap<String,Integer> indexValChangeMap = new HashMap<>();

        String delimiter = "_";
        for(int row = 0; row< board.length; row++){
            for(int col=0;col<board[0].length;col++){
                int liveNeighbour = countNeighbours(board,row,col);
                //live cell
                // if live => board[i][j] == 1 :
                if(board[row][col] == 1){
                    // and live neighbours are less than 2, then board[i][j] = 0
                    // and live neighbours == 2 || == 3, then do nothing
                    // and live neighbours > 3, then board[i][j] == 0
                    if(liveNeighbour<2 || liveNeighbour > 3){
                        indexValChangeMap.put(row + delimiter + col,0);
                    }
                }

                //dead cell
                // if dead => board[i][j] == 0 :
                // and live neighbours == 3 , then board[i][j] = 1
                if(board[row][col] == 0 && liveNeighbour == 3){
                    indexValChangeMap.put(row + delimiter + col,1);
                }
            }
        }

        indexValChangeMap.forEach((key, value) -> {
            String[] parts = key.split(delimiter);
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            board[row][col] = value;
        });
    }

}
