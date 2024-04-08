package com.zj.springboottest.leetcocde;

import java.util.Arrays;

/**
 * @Author zhangjin
 * @Date 2024/2/27 15:26
 * @description:
 */
public class 寻找两个正序数组的中位数4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        Solution solution = new Solution();
        double result = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);

    }
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            double result = 0;
            int [] nums = new int[nums1.length+nums2.length];
            for (int i = 0; i < nums1.length; i++) {
                nums[i] = nums1[i];
            }
            for (int i = 0; i < nums2.length; i++) {
                nums[i+nums1.length] = nums2[i];
            }
            Arrays.sort(nums);
            if (nums.length%2==0){
                result = (nums[nums.length/2-1]+nums[nums.length/2])/2.0;
            }else {
                result = nums[nums.length/2];
            }
            return result;
        }
    }
}
