package com.company.Trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    /**
     * https://leetcode.com/problems/word-search-ii/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=DMP2bqW6URA
     * */

    private List<String> result = new ArrayList<>();
    private int rows, cols;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class TrieNode {
        boolean endOfWord;
        TrieNode[] children = new TrieNode[26];
        String word = null;  // store complete word at end node
    }

    private TrieNode getNode() {
        return new TrieNode();
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = getNode();
            }
            curr = curr.children[idx];
        }
        curr.endOfWord = true;
        curr.word = word;
    }

    private void dfs(char[][] board, int i, int j, TrieNode root) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] == '$' || root.children[board[i][j] - 'a'] == null) {
            return;
        }

        char ch = board[i][j];
        root = root.children[ch - 'a'];

        if (root.endOfWord) {
            result.add(root.word);
            root.endOfWord = false; // avoid duplicate entries
        }

        board[i][j] = '$';  // mark visited

        for (int[] dir : directions) {
            dfs(board, i + dir[0], j + dir[1], root);
        }

        board[i][j] = ch; // restore
    }

    public List<String> findWords(char[][] board, String[] words) {
        rows = board.length;
        cols = board[0].length;

        TrieNode root = getNode();
        for (String word : words) {
            insert(root, word);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = board[i][j];
                if (root.children[ch - 'a'] != null) {
                    dfs(board, i, j, root);
                }
            }
        }

        return result;
    }
}
