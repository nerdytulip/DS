package com.company.Graph;

import java.util.List;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        dfs(rooms,0, visited);

        for( boolean b : visited){
            if(!b){
                return false;
            }
        }

        return true;
    }

    private void dfs(List<List<Integer>> rooms, int i,boolean[] visited) {
        for(int key : rooms.get(i)){
            if(!visited[key]){
                visited[key] = true;
                dfs(rooms,key,visited);
            }
        }
    }
}
