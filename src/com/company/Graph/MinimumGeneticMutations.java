package com.company.Graph;

import java.util.*;

public class MinimumGeneticMutations {

    /**
     * https://leetcode.com/problems/minimum-genetic-mutation/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=H3kSFSv-t30
     * */

    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(start);
        visited.add(start);

        int level = 0;
        char[] genes = {'A', 'C', 'G', 'T'};

        while(!queue.isEmpty()){
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                String curr = queue.poll();

                if (curr.equals(end)) {
                    return level;
                }

                char[] currArr = curr.toCharArray();
                for (int j = 0; j < currArr.length; j++) {
                    char oldChar = currArr[j];
                    for (char ch : genes) {
                        currArr[j] = ch;
                        String neighbor = new String(currArr);

                        if (!visited.contains(neighbor) && bankSet.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                    currArr[j] = oldChar; // restore original
                }
            }
            level++;
        }

        return -1;
    }
}
