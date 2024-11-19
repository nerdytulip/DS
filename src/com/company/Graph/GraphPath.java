package com.company.Graph;

import java.util.*;
import java.util.LinkedList;

public class GraphPath {

    public boolean validPath_list(int n, int[][] edges, int source, int destination){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge:edges){
            int u = edge[0];
            int v = edge[1];

            graph.computeIfAbsent(u, value -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, value -> new ArrayList<>()).add(u);
        }

        boolean[] visited = new boolean[n];

//        return dfs_list(graph,source,destination,visited,n);
        return bfs_list(graph,source,destination,visited,n);

    }

    private boolean dfs_list(Map<Integer, List<Integer>> graph, int source, int destination, boolean[] visited, int n) {

        if(source == destination){
            return true;
        }

        visited[source] = true;

        for( int neighbor : graph.get(source)){
            if(!visited[neighbor]){
                if(dfs_list(graph,neighbor,destination,visited,n)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs_list(Map<Integer, List<Integer>> graph, int src, int dest, boolean[] visited, int n) {

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = true;

        while(!q.isEmpty()){
            int curr = q.remove();

            if(curr == dest){
                return true;
            }

            for(int neighbor : graph.get(curr)){
                if(!visited[neighbor]){
                    q.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }


    public boolean validPath_matrix(int n, int[][] edges, int source, int destination) {

        //initialize adjacency matrix
        boolean[][] graph = new boolean[n][n];
        for(boolean[] row : graph){
            Arrays.fill(row,false);
        }
        for(int[] edge:edges){
            int u = edge[0];
            int v = edge[1];
            graph[u][v] = true;
            graph[v][u] = true;
        }

        boolean[] visited = new boolean[n];

//      return dfs(graph,source,destination,visited,n);

        return bfs_matrix(graph,source,destination,visited,n);
    }

    private boolean dfs_matrix(boolean[][] graph, int src, int dest,boolean[] visited, int n) {
        if(src == dest){
            return true;
        }
        //marks visited
        visited[src] = true;

        //parse rows
        for(int i=0;i<n;i++){
            //neighbours
            if(graph[src][i] && !visited[i]){
                //i because 1 is the neighbour and now you will try to recursively find it neighbour
                if(dfs_matrix(graph,i,dest,visited,n)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs_matrix(boolean[][] graph, int src, int dest,boolean[] visited, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        while(!q.isEmpty()){
            int curr = q.remove();

            if(curr == dest){
                return true;
            }

            //neighbours of current
            for(int i=0;i<n; i++){
                //confirm of curr and i are connected and is not visited
                if(graph[curr][i] == true && !visited[i]){
                    q.add(i);
                    // we mark it visited while added to queue, so that we only add to queue if it is not visited
                    visited[i] = true;
                }
            }
        }

        return false;
    }



    public static void main(String[] args){
        GraphPath graphPath = new GraphPath();
        int n = 3; int[][] edges = {{0,1},{1,2},{2,0}};
        int source = 0;
        int destination = 2;

        boolean b = graphPath.validPath_list(n, edges, source, destination);
        System.out.println(b);
    }
}
