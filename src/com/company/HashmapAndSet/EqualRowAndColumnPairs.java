package com.company.HashmapAndSet;

import java.util.Arrays;
import java.util.HashMap;

public class EqualRowAndColumnPairs {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        HashMap<String,Integer> rowElementsMap = new HashMap<>();
        int matchingCount = 0;
        for(int[] row : grid){
            String key = Arrays.toString(row);
            rowElementsMap.put(key,1 + rowElementsMap.getOrDefault(key,0));
        }

        for(int column = 0;column<n;column++){
            int[] columnValue = new int[n];
            for(int row =0;row<n;row++){
                columnValue[row] = grid[row][column];
            }
            String colKey = Arrays.toString(columnValue);
            if(rowElementsMap.containsKey(colKey)){
                matchingCount += rowElementsMap.get(colKey);
            }
        }

        return matchingCount;
    }
}
