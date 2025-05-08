package com.company.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class IPO {
    /**
     * https://leetcode.com/problems/ipo/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=b12SZXrZF9I
     * */
    class Project{
        int capital;
        int profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        public int getCapital() {
            return capital;
        }

        public int getProfit() {
            return profit;
        }
    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        List<Project> projectList = new ArrayList<>();

        for(int i=0;i<n;i++){
            projectList.add(new Project(capital[i],profits[i]));
        }

        projectList.sort((a,b) -> a.capital - b.capital);

        // Max-heap ordered by profit (descending)
        PriorityQueue<Project> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getProfit() - a.getProfit()
        );

        int i = 0;
        while (k-- > 0) {
            // Add all projects we can afford
            while (i < n && projectList.get(i).getCapital() <= w) {
                maxHeap.offer(projectList.get(i));
                i++;
            }

            if (maxHeap.isEmpty()) break;

            // Pick the most profitable project
            w += maxHeap.poll().getProfit();
        }

        return w;
    }
}
