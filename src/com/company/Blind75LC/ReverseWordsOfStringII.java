package com.company.Blind75LC;

public class ReverseWordsOfStringII {
    public String reverseWords(char[] s) {
        int n = s.length;
        reverseArray(s,0, n - 1);

        // Step 2: Reverse each word in place
        int start = 0;

        for (int end = 0; end < n; end++) {
            if (s[end] == ' ') {
                reverseArray(s, start, end - 1);
                start = end + 1;
            }
        }
        // Reverse the last word (since there's no trailing space)
        reverseArray(s, start, n - 1);
        
        return s.toString();
    }

    private static void reverseArray(char[] s, int l, int r) {

        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }
}
