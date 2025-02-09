package com.company.Greedy;

import java.util.*;

public class JobSequencing {

    // Function to schedule jobs and maximize profit
    public ArrayList<Integer> JobSequencing(int[] id, int[] deadline, int[] profit) {
        int n = id.length;
        List<Job> jobs = new ArrayList<>();

        // Step 1: Store job details in a list
        for (int i = 0; i < n; i++) {
            jobs.add(new Job(id[i], deadline[i], profit[i]));
        }

        // Step 2: Sort jobs by descending order of profit
        jobs.sort((a, b) -> Integer.compare(b.profit, a.profit));

        // Step 3: Find the maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 4: Create a schedule array to track occupied slots
        int[] schedule = new int[maxDeadline + 1];
        Arrays.fill(schedule, -1); // -1 means slot is free

        int jobCount = 0, totalProfit = 0;

        // Step 5: Assign jobs to the latest available slot before their deadline
        for (Job job : jobs) {
            for (int j = job.deadline; j > 0; j--) {
                if (schedule[j] == -1) { // If slot is free
                    schedule[j] = job.id; // Assign job
                    jobCount++;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Step 6: Return result as ArrayList
        return new ArrayList<>(Arrays.asList(jobCount, totalProfit));
    }

    // Helper class to store job details
    static class Job {
        int id, deadline, profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        JobSequencing js = new JobSequencing();
        int[] id = {1, 2, 3, 4, 5};
        int[] deadline = {2, 1, 2, 1, 3};
        int[] profit = {100, 19, 27, 25, 15};

        ArrayList<Integer> result = js.JobSequencing(id, deadline, profit);
        System.out.println("Jobs scheduled: " + result.get(0));
        System.out.println("Total Profit: " + result.get(1));
    }
}

