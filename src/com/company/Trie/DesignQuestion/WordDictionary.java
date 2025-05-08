package com.company.Trie.DesignQuestion;

class WordDictionary {

    /**
     * https://leetcode.com/problems/design-add-and-search-words-data-structure/submissions/1625815066/?envType=study-plan-v2&envId=top-interview-150
     * */
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;

        boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }

        TrieNode get(char ch) {
            return children[ch - 'a'];
        }

        void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(index);
        if (ch == '.') {
            for (TrieNode child : node.children) {
                if (child != null && searchInNode(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            if (!node.containsKey(ch)) return false;
            return searchInNode(word, index + 1, node.get(ch));
        }
    }
}

