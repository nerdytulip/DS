package com.company.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    /**
     * https://leetcode.com/problems/combinations/editorial/?envType=study-plan-v2&envId=top-interview-150
     * */
    List<List<Integer>> result = new ArrayList<>();

    public void backtrack(int start, int n, int k, List<Integer> temp) {
        if (k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(i + 1, n, k - 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k, new ArrayList<>());
        return result;
    }
}
