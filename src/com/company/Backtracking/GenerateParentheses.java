package com.company.Backtracking;

import java.util.*;

public class GenerateParentheses {
    /**
     * https://leetcode.com/problems/generate-parentheses/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=7xkPbffc6w8
     * */

    private List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        solve(n, "", 0, 0);
        return result;
    }

    private void solve(int n, String curr, int open, int close) {
        if (curr.length() == 2 * n) {
            result.add(curr);
            return;
        }

        if (open < n) {
            curr += '(';
            solve(n, curr, open + 1, close);
            curr = curr.substring(0, curr.length() - 1);
        }
        if (close < open) {
            curr += ')';
            solve(n, curr, open, close + 1);
            curr = curr.substring(0, curr.length() - 1);
        }
    }
}
