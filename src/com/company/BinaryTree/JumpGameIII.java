package com.company.BinaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

public class JumpGameIII {

    /**
     * https://leetcode.com/problems/jump-game-iii/description/
     *
     * https://www.youtube.com/watch?v=FVkYM-GjiQQ&list=PLFdAYMIVJQHPswf74XJDtuWV-4BOeP65l&index=26
     *
     * we used level order traversal
     * */

    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int curr = queue.poll();

            if(arr[curr] == 0){
                return true;
            }

            // skip if negative
            if(arr[curr] < 0){
                continue;
            }

            // try both directions

            if(curr + arr[curr] < arr.length){
                queue.add(curr+arr[curr]);
            }

            if(curr - arr[curr] >= 0){
                queue.add(curr - arr[curr]);
            }

            // mark element as visited
            arr[curr] = -arr[curr];
        }

        return false;
    }
}
