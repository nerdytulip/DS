package com.company.DP.DPWithMergeInterval;

import com.company.BinaryTree.NodeDTO.TreeNode;

import java.util.*;

public class UniqueBSTII {
    // Simple immutable key for the range [start..end]
    private static class Pair {
        final int start, end;
        Pair(int s, int e) { this.start = s; this.end = e; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return start == p.start && end == p.end;
        }
        @Override public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    // shared memo map
    private final Map<Pair, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();  // edge case
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        Pair key = new Pair(start, end);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            // one empty tree
            res.add(null);
        } else {
            for (int i = start; i <= end; i++) {
                // **fixed**: just two-arg calls here
                List<TreeNode> leftTrees  = buildTrees(start, i - 1);
                List<TreeNode> rightTrees = buildTrees(i + 1, end);
                for (TreeNode L : leftTrees) {
                    for (TreeNode R : rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left  = L;
                        root.right = R;
                        res.add(root);
                    }
                }
            }
        }

        memo.put(key, res);
        return res;
    }
}
