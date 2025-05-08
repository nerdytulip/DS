package com.company.Graph;

import java.util.*;

public class WordLadder {
    /**
     * https://leetcode.com/problems/word-ladder/?envType=study-plan-v2&envId=top-interview-150
     *
     * */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // The beginWord itself counts as the first step in the transformation sequence
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                char[] currArray = curr.toCharArray();

                for (int j = 0; j < currArray.length; j++) {
                    char originalChar = currArray[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        currArray[j] = c;
                        String nextWord = new String(currArray);

                        if (nextWord.equals(endWord)) {
                            return level + 1;
                        }

                        if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                            visited.add(nextWord);
                            queue.offer(nextWord);
                        }
                    }

                    currArray[j] = originalChar; // restore for next round
                }
            }

            level++;
        }

        return 0; // no path found
    }
}
