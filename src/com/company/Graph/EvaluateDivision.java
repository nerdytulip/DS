package com.company.Graph;

import java.util.*;

public class EvaluateDivision {
    // Inner helper class to represent an edge in the graph.
    // Each edge holds a neighbor and the associated multiplication factor.
    // https://leetcode.com/problems/evaluate-division/description/?envType=study-plan-v2&envId=top-interview-150
    // https://www.youtube.com/watch?v=Uei1fwDoyKk
    private static class Edge {
        String neighbor;
        double value;

        public Edge(String neighbor, double value) {
            this.neighbor = neighbor;
            this.value = value;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build the adjacencyList: variable -> list of (neighbor, value)
        Map<String, List<Edge>> adjacencyList = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String numerator = equation.get(0);
            String denominator = equation.get(1);
            double value = values[i];

            adjacencyList.computeIfAbsent(numerator, k -> new ArrayList<>()).add(new Edge(denominator, value));
            adjacencyList.computeIfAbsent(denominator, k -> new ArrayList<>()).add(new Edge(numerator, 1.0 / value));
        }

        // Process each query using DFS.
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String numerator = query.get(0);
            String denominator = query.get(1);

            // If one of the variables is not in the adjacencyList, the result is -1.
            if (!adjacencyList.containsKey(numerator) || !adjacencyList.containsKey(denominator)) {
                results[i] = -1.0;
            } else {
                // Use a set to record visited variables (to prevent infinite loops).
                Set<String> visited = new HashSet<>();
                visited.add(numerator);
                results[i] = dfs(numerator, denominator, visited, adjacencyList);
            }
        }
        return results;
    }

    // DFS helper method to find the quotient from 'numerator' to 'denominator'.
    // 'visited' contains the nodes that have been visited along the numerator search path.
    private double dfs(String numerator, String denominator, Set<String> visited, Map<String, List<Edge>> adjacencyList) {
        // When the numerator variable equals the denominator, return multiplicative identity.
        if (numerator.equals(denominator)) {
            return 1.0;
        }

        // Explore each edge (neighbor with multiplication factor) from the numerator node.
        for (Edge edge : adjacencyList.get(numerator)) {
            if (!visited.contains(edge.neighbor)) {
                visited.add(edge.neighbor);
                double result = dfs(edge.neighbor, denominator, visited, adjacencyList);
                // If a valid result is found (i.e. not -1.0), multiply with the numerator factor.
                if (result != -1.0) {
                    return result * edge.value;
                }else{
                    // Backtrack if not found.
                    visited.remove(edge.neighbor);
                }
            }
        }
        // No valid path found.
        return -1.0;
    }

    // For testing purposes.
    public static void main(String[] args) {
        EvaluateDivision sol = new EvaluateDivision();

        // Example input:
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        double[] results = sol.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(results)); // Expected output: [6.0, 0.5, -1.0, 1.0, -1.0]
    }
}
