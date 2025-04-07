package com.company.DP.DPWithMergeInterval;

import java.math.BigInteger;

public class MinScoreTriangulation {

    /**
     * For a convex polygon with n vertices, the number of ways to triangulate it
     * is given by the (n-2)th Catalan number.
     *
     * The Catalan sequence is defined as:
     *   C_0 = 1, C_1 = 1, C_2 = 2, C_3 = 5, C_4 = 14, ...
     *
     * For example:
     *   - A triangle (n = 3) has C_(3-2) = C_1 = 1 triangulation.
     *   - A quadrilateral (n = 4) has C_(4-2) = C_2 = 2 triangulations.
     *   - A pentagon (n = 5) has C_(5-2) = C_3 = 5 triangulations.
     *
     * This code uses dynamic programming to compute the Catalan numbers using the recurrence:
     *   C_0 = 1
     *   C_i = sum(C_j * C_{i-1-j}) for j = 0 to i-1.
     *
     *   https://www.geeksforgeeks.org/catalan-numbers/
     *   https://www.geeksforgeeks.org/number-of-ways-a-convex-polygon-of-n2-sides-can-split-into-triangles-by-connecting-vertices/
     */

    // Returns the number of ways to triangulate a convex polygon with n vertices.
    // This equals the (n-2)th Catalan number.
    public static BigInteger countTriangulations(int n) {
        if (n < 3) {
            return BigInteger.ZERO; // No triangulation possible for fewer than 3 vertices.
        }
        int m = n - 2; // m corresponds to the Catalan index
        BigInteger[] dp = new BigInteger[m + 1];
        dp[0] = BigInteger.ONE; // Base case: A triangle (3 vertices) has 1 triangulation.

        // Build the DP table from 1 to m.
        for (int i = 1; i <= m; i++) {
            dp[i] = BigInteger.ZERO;
            for (int j = 0; j < i; j++) {
                dp[i] = dp[i].add(dp[j].multiply(dp[i - 1 - j]));
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        int vertices = 7; // Example: a convex polygon with 7 vertices.
        BigInteger ways = countTriangulations(vertices);
        System.out.println("A convex polygon with " + vertices + " vertices can be triangulated in " + ways + " ways.");
    }

}
