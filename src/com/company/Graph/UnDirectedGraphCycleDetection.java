package com.company.Graph;

import java.util.*;

public class UnDirectedGraphCycleDetection {

    // Using DFS
    public boolean hasCycle_DFS(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = buildGraph(n, edges);
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, visited, adj)) return true;
            }
        }

        return false;
    }

    private boolean dfs(int current, int parent, boolean[] visited, Map<Integer, List<Integer>> adj) {
        visited[current] = true;

        for (int neighbor : adj.getOrDefault(current, new ArrayList<>())) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, current, visited, adj)) return true;
            } else if (neighbor != parent) {
                return true; // Visited neighbor that's not the parent → cycle
            }
        }

        return false;
    }

    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // bidirectional
        }
        return adj;
    }

    // Using BFS
    public boolean hasCycle_BFS(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = buildGraph(n, edges);
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (bfs(i, visited, adj)) return true;
            }
        }

        return false;
    }

    private boolean bfs(int start, boolean[] visited, Map<Integer, List<Integer>> adj) {
        Queue<int[]> queue = new LinkedList<>(); // [node, parent]
        queue.offer(new int[]{start, -1});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int parent = curr[1];

            for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, node});
                } else if (neighbor != parent) {
                    return true; // Visited node not equal to parent → cycle
                }
            }
        }

        return false;
    }


}
