package com.company.Greedy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {

    class Task{
        int count;
        int time;
        public Task(int count, int time){
            this.count = count;
            this.time = time;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        int time = 0;
        int[] freq = new int[26];
        //make priority queue
        //negative cuz if we do positive then , the one which has minimum frequency will be on top
        PriorityQueue<Task> pq = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return -Integer.compare(t1.count,t2.count);
            }
        });

        for(char task:tasks){
            freq[task-'A']++;
        }

        for(int i=0;i<26;i++){
            if(freq[i]>=1){
                pq.add(new Task(freq[i],0));
            }
        }

        Queue<Task> q = new LinkedList<>();

        while(!pq.isEmpty() || !q.isEmpty()){
            if(!q.isEmpty() && time - q.peek().time > n){
                pq.add(q.remove());
            }

            if(!pq.isEmpty()){
                Task t = pq.remove();
                t.count--;
                t.time = time;
                if(t.count>0){
                    q.add(t);
                }
            }
            time++;
        }

        return time;
    }

    public static void main(String[] args){
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        TaskScheduler taskScheduler = new TaskScheduler();
        int time = taskScheduler.leastInterval(tasks,n);
        System.out.println("time "+time);
    }
}
