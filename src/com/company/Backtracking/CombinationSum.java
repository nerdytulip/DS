package com.company.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    /**
     * https://leetcode.com/problems/combination-sum/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=GBKI9VSKdGg&t=23s
     * */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, candidates, target, new ArrayList<>(), 0, res);
        return res;
    }

    private void dfs(int i, int[] candidates, int target, List<Integer> curr, int total, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if (i >= candidates.length || total > target) {
            return;
        }

        // Include current candidate
        curr.add(candidates[i]);
        dfs(i, candidates, target, curr, total + candidates[i], res);
        curr.remove(curr.size() - 1);

        // Exclude and move to next
        dfs(i + 1, candidates, target, curr, total, res);
    }
}
