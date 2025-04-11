- **Fibonacci Type (N Step Dependency)**
    - **Description:**  
      Problems where the solution depends on the last one or two computed values.
    - **Heuristic:**
        - **Commmon Recurrence:** 

           - `dp[i] = dp[i-1] + dp[i-2]` → 2-step (e.g., **Climbing Stairs**)
           - `dp[i] = min(dp[i-1], dp[i-7], dp[i-30]) + cost` → multi-step (e.g., **Min Cost for Tickets**)
           - `dp[i] = max(dp[i-1], dp[i-2] + arr[i])` → non-adjacent elements (e.g., **House Robber**)
        - **Space Optimization:** Use two variables instead of an array.
    - **Problems:**
        - Climbing Stairs
        - House Robber
        - House Robber II
        - N-th Tribonacci Number
        - Maximum Sum of Non-Adjacent Elements
        - Paint House
        - Number of Derangements
