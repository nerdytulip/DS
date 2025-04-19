package com.company.Graph;

import java.util.*;

public class ReorderRoutes {
    // Helper class to represent a pair of values consisting of a node and a boolean indicating the edge's direction
    public static class Edge {
        int node;
        boolean isDirected;

        public Edge(int node, boolean isDirected) {
            this.node = node;
            this.isDirected = isDirected;
        }
    }

    // Main method to find the minimum reorder.
    public int minReorder(int n, int[][] connections) {
        // Create a graph represented as an adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] connection : connections) {
            int from = connection[0], to = connection[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, true));
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, false));
        }
        boolean[] visited = new boolean[n]; // Track visited nodes
        return dfs(0, graph, visited); // Perform DFS starting from node 0
    }

    // Recursive DFS to accumulate the count of edges that need to be redirected
    private int dfs(int currentNode, Map<Integer, List<Edge>> graph, boolean[] visited) {
        visited[currentNode] = true; // Mark the current node as visited
        int reorderCount = 0; // Initialize the reorder count for the current node
        // Get the edges connected to the current node; if not present, get an empty list
        List<Edge> edges = graph.getOrDefault(currentNode, Collections.emptyList());
        for (Edge edge : edges) {
            int nextNode = edge.node; // The node at the other end of the edge
            boolean isDirected = edge.isDirected; // If the edge direction is from currentNode to nextNode
            if (!visited[nextNode]) { // If the next node has not been visited
                if (isDirected) {
                    // If the current edge is directed towards the next node, it needs to be reordered.
                    reorderCount++;
                }
                // Add the reorders required from deeper levels of the graph
                reorderCount += dfs(nextNode, graph, visited);
            }
        }
        return reorderCount; // Return the total count of reorderings for this branch
    }
}
