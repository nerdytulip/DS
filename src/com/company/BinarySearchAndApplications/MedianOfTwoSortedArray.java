package com.company.BinarySearchAndApplications;

public class MedianOfTwoSortedArray {

    /**
     * median
     * - odd length array - middle element
     * - even length array = sum of middle elements / 2
     *
     *
     * https://leetcode.com/problems/median-of-two-sorted-arrays/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=q6IEA26hvXc
     * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A = nums1, B = nums2;
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int m = A.length;
        int n = B.length;
        int total = m + n;
        int half = (m + n + 1) / 2;

        int l = 0, r = m;

        while (l <= r) {
            int i = (l + r) / 2;
            int j = half - i;

            int Aleft = (i == 0) ? Integer.MIN_VALUE : A[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : A[i];

            int Bleft = (j == 0) ? Integer.MIN_VALUE : B[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                } else {
                    return Math.max(Aleft, Bleft);
                }
            } else if (Aleft > Bright) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }
}
