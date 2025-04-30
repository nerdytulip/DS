package com.company.Trie;

public class LongestCommonPrefix {

    /**
     * https://leetcode.com/problems/longest-common-prefix/?envType=study-plan-v2&envId=top-interview-150
     * */
    public String longestCommonPrefix(String q, String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        Trie trie = new Trie();
        for (int i = 1; i < strs.length; i++) {
            trie.insert(strs[i]);
        }
        return trie.searchLongestPrefix(q);
    }

}
