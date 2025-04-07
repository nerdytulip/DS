- **0/1 Knapsack Type (0/1 Choice-Based)**
    - **Description:**  
      Problems that involve choosing items optimally under constraints.
    - **Heuristic:**
        - **Recurrence:** `dp[i][capacity] = max(dp[i-1][capacity], value[i] + dp[i-1][capacity - weight[i]])`
    - **Problems:**
        - 0/1 Knapsack
        - Job Scheduling with Deadlines
