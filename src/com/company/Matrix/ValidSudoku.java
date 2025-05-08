package com.company.Matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku {
    /**
     * https://leetcode.com/problems/valid-sudoku/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>(); // key = r/3 + "-" + c/3

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];

                if (val == '.') continue;

                // Initialize sets if not already
                rows.putIfAbsent(r, new HashSet<>());
                cols.putIfAbsent(c, new HashSet<>());
                String squareKey = (r / 3) + "-" + (c / 3);
                squares.putIfAbsent(squareKey, new HashSet<>());

                // Check for duplicates
                if (rows.get(r).contains(val) ||
                        cols.get(c).contains(val) ||
                        squares.get(squareKey).contains(val)) {
                    return false;
                }

                // Add value to sets
                rows.get(r).add(val);
                cols.get(c).add(val);
                squares.get(squareKey).add(val);
            }
        }

        return true;
    }
}
