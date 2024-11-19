package com.company.TwoPointer;

import java.util.Arrays;

public class MergeSortedArrays {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int midx = m -1;
        int nidx = n -1;
        int k = m+n -1;

        while (midx >= 0 && nidx >= 0) {
            if ( nums1[midx] > nums2[nidx]) {
                nums1[k] = nums1[midx];
                midx--;
            } else {
                nums1[k] = nums2[nidx];
                nidx--;
            }
            k--;
        }

        while(nidx>=0){
            nums1[k--] = nums2[nidx--];
        }
    }

    public static void main(String[] args){
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};

        MergeSortedArrays sortedArrays = new MergeSortedArrays();
        sortedArrays.merge(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));
    }
}
