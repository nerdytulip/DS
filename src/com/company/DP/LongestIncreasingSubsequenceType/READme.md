- **Longest Increasing Subsequence (LIS) Type**
    - **Description:**  
      Problems where you need to find the longest increasing subsequence or a variant.
    - **Recurrence:**
        - `dp[i] = max(dp[j] + 1)` for all `j < i` where `arr[j] < arr[i]`
    - **Problems:**
        - Longest Increasing Subsequence
        - Russian Doll Envelopes
        - Maximum Height by Stacking Cuboids
