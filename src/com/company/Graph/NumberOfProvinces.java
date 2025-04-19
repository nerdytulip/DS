package com.company.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces {
    /**
     * https://leetcode.com/problems/number-of-provinces/description/?envType=study-plan-v2&envId=leetcode-75
     * https://www.youtube.com/watch?v=zsJkNfqnPC4
     * */

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int cnt = 0;

        for(int i =0;i<n;i++){
            if(!visited[i]){
                cnt++;
                dfs(isConnected,visited,i,n);
            }
        }

        return cnt;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int curr, int n) {
        for(int i =0;i<n;i++){
            if (isConnected[curr][i] == 1 && !visited[i]){
                visited[i] = true;
                dfs(isConnected,visited,i,n);
            }
        }
    }

    // we can also use use bfs as well
    public int findCircleNumBfs(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int cnt = 0;

        for(int i =0;i<n;i++){
            if(!visited[i]){
                cnt++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                while(!queue.isEmpty()){
                    int curr = queue.remove();

                    for(int k= 0;k<n;k++){
                        if (isConnected[curr][i] == 1 && !visited[i]){
                            visited[i] = true;
                            queue.add(k);
                        }
                    }
                }
            }
        }

        return cnt;
    }

}
