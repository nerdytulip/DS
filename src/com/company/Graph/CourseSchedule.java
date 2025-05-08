package com.company.Graph;

import java.util.*;

public class CourseSchedule {

    /**
     *
     * DAG - Directed Graph
     * https://leetcode.com/problems/course-schedule/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=lqjlGGMjSMU
     *
     *
     * 🔹 What is Kahn’s Algorithm?
     * Kahn’s Algorithm:
     *
     * Uses BFS (Breadth-First Search).
     *
     * Tracks indegree of each node (number of incoming edges).
     *
     * Repeatedly picks nodes with indegree 0 and removes them from the graph.
     *
     * If all nodes can be processed this way, the graph is acyclic.
     *
     * If some nodes remain with non-zero indegree, there's a cycle.
     *
     * 🔹 Background: What is indegree[v]?
     * indegree[v] = Number of incoming edges to node v.
     *
     * If indegree[v] == 0, it means all of v's prerequisites (or dependencies) are already completed (visited).
     * */
    // Approach-1 (Using BFS Cycle Check - Kahn's Algorithm (Topological Sort)
    public boolean canFinish_BFS(int numCourses, int[][] prerequisites) {
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
        return topologicalSortCheck(adj, numCourses, indegree);
    }

    private boolean topologicalSortCheck(Map<Integer, List<Integer>> adj, int n, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        // Add nodes with 0 indegree to the queue
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
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
                    queue.offer(v);
                    count++;
                }
            }
        }

        // Return true if all nodes were processed (i.e., no cycle)
        return count == n;
    }

    /**
     * https://www.youtube.com/watch?v=X1TIkW4C254&t=50s
     * */
    //Approach-2 (Using DFS Cycle Check)
    public boolean canFinish_DFS(int numCourses, int[][] prerequisites) {
        // Build the adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] pair : prerequisites) {
            int a = pair[0];
            int b = pair[1];
            adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a); // b -> a
        }

        // Arrays to track visited and recursion stack
        boolean[] visited = new boolean[numCourses];
        boolean[] inRecursion = new boolean[numCourses];

        // Run DFS for each course
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && isCycleDFS(adj, i, visited, inRecursion)) {
                return false; // cycle detected
            }
        }

        return true; // no cycle, can finish all courses
    }

    private boolean isCycleDFS(Map<Integer, List<Integer>> adj, int u, boolean[] visited, boolean[] inRecursion) {
        visited[u] = true;
        inRecursion[u] = true;

        for (int v : adj.getOrDefault(u, Collections.emptyList())) {
            if (!visited[v] && isCycleDFS(adj, v, visited, inRecursion)) {
                return true;
            } else if (inRecursion[v]) {
                return true; // cycle detected
            }
        }

        inRecursion[u] = false;
        return false;
    }

}
