package com.company.Graph;

import java.util.*;

public class CourseScheduleII {
    /**
     * https://leetcode.com/problems/course-schedule-ii/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=W1WhSN9wAw0&list=PLpIkg8OmuX-LZB9jYzbbZchk277H5CbdY&index=13
     * */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build adjacency list and indegree array
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int[] pair : prerequisites) {
            int a = pair[0];
            int b = pair[1];

            // b -> a
            adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            indegree[a]++;
        }

        // Call helper method to check topological sort
        return topologicalSortCheck(adj, numCourses, indegree).stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> topologicalSortCheck(Map<Integer, List<Integer>> adj, int n, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        int count = 0;

        // Add nodes with 0 indegree to the queue
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                result.add(i);
                queue.offer(i);
                count++;
            }
        }

        // Kahn's Algorithm
        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj.getOrDefault(u, new ArrayList<>())) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    result.add(v);
                    queue.offer(v);
                    count++;
                }
            }
        }

        // Return true if all nodes were processed (i.e., no cycle)
        if(count == n){
            return result;
        }else{
            return new ArrayList<>();
        }
    }
}
