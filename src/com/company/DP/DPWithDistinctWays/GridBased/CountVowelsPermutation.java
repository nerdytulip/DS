package com.company.DP.DPWithDistinctWays.GridBased;

import java.util.HashMap;
import java.util.Map;

public class CountVowelsPermutation {
    private static final int MOD = 1_000_000_007;

    private long solveRec(int n, int index, char lastVowel) {
        if (index == n) {
            return 1; // successfully built a string of length n
        }

        long count = 0;

        if (lastVowel == ' ') {
            // start - can place any vowel
            count += solveRec(n, index + 1, 'a');
            count += solveRec(n, index + 1, 'e');
            count += solveRec(n, index + 1, 'i');
            count += solveRec(n, index + 1, 'o');
            count += solveRec(n, index + 1, 'u');
        } else if (lastVowel == 'a') {
            count += solveRec(n, index + 1, 'e');
        } else if (lastVowel == 'e') {
            count += solveRec(n, index + 1, 'a');
            count += solveRec(n, index + 1, 'i');
        } else if (lastVowel == 'i') {
            count += solveRec(n, index + 1, 'a');
            count += solveRec(n, index + 1, 'e');
            count += solveRec(n, index + 1, 'o');
            count += solveRec(n, index + 1, 'u');
        } else if (lastVowel == 'o') {
            count += solveRec(n, index + 1, 'i');
            count += solveRec(n, index + 1, 'u');
        } else if (lastVowel == 'u') {
            count += solveRec(n, index + 1, 'a');
        }

        return count % MOD;
    }

    public int countVowelPermutationRec(int n) {
        return (int) solveRec(n, 0, ' '); // start from position 0 and no previous character
    }

    private long solveMem(int n, int index, char lastVowel, Map<String, Long> memo) {
        if (index == n) {
            return 1; // successfully built a string of length n
        }

        // Create memoization key from (index, lastVowel)
        String key = index + "_" + lastVowel;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        long count = 0;

        if (lastVowel == ' ') {
            // start - can place any vowel
            count += solveMem(n, index + 1, 'a',memo);
            count += solveMem(n, index + 1, 'e',memo);
            count += solveMem(n, index + 1, 'i',memo);
            count += solveMem(n, index + 1, 'o',memo);
            count += solveMem(n, index + 1, 'u',memo);
        } else if (lastVowel == 'a') {
            count += solveMem(n, index + 1, 'e',memo);
        } else if (lastVowel == 'e') {
            count += solveMem(n, index + 1, 'a',memo);
            count += solveMem(n, index + 1, 'i',memo);
        } else if (lastVowel == 'i') {
            count += solveMem(n, index + 1, 'a',memo);
            count += solveMem(n, index + 1, 'e',memo);
            count += solveMem(n, index + 1, 'o',memo);
            count += solveMem(n, index + 1, 'u',memo);
        } else if (lastVowel == 'o') {
            count += solveMem(n, index + 1, 'i',memo);
            count += solveMem(n, index + 1, 'u',memo);
        } else if (lastVowel == 'u') {
            count += solveMem(n, index + 1, 'a',memo);
        }

        count %= MOD;
        memo.put(key, count);
        return count;
    }

    public int countVowelPermutationMem(int n) {
        Map<String, Long> memo = new HashMap<>();
        return (int) solveMem(n, 0, ' ',memo); // start from position 0 and no previous character
    }

    public int countVowelPermutationTab(int n) {

        // i is length
        // 5 vowels: a=0, e=1, i=2, o=3, u=4
        long[][] dp = new long[n+1][5]; // now dp[i][vowel] = number of strings of length i ending with vowel

        // Base case: strings of length 1
        for (int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i-1][1] + dp[i-1][2] + dp[i-1][4]) % MOD; // 'a' after 'e','i','u'
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD; // 'e' after 'a','i'
            dp[i][2] = (dp[i-1][1] + dp[i-1][3]) % MOD; // 'i' after 'e','o'
            dp[i][3] = (dp[i-1][2]) % MOD; // 'o' after 'i'
            dp[i][4] = (dp[i-1][2] + dp[i-1][3]) % MOD; // 'u' after 'i','o'
        }

        long total = 0;
        for (int i = 0; i < 5; i++) {
            total = (total + dp[n][i]) % MOD;
        }

        return (int) total;
    }

    public int countVowelPermutation(int n) {
        // initialize count for strings of length 1
        long a = 1, e = 1, i = 1, o = 1, u = 1;

        for (int j = 2; j <= n; j++) {
            long newA = (e + i + u) % MOD; // 'a' after 'e','i','u'
            long newE = (a + i) % MOD; // 'e' after 'a','i'
            long newI = (e + o) % MOD; // 'i' after 'e','o'
            long newO = (i) % MOD; // 'o' after 'i'
            long newU = (i + o) % MOD; // 'u' after 'i','o'

            a = newA;
            e = newE;
            i = newI;
            o = newO;
            u = newU;
        }

        return (int)((a + e + i + o + u) % MOD);
    }
}
