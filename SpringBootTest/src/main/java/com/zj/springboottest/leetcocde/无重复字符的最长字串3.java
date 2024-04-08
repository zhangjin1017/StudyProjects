package com.zj.springboottest.leetcocde;

/**
 * @Author zhangjin
 * @Date 2024/2/27 13:57
 * @description:
 */
public class 无重复字符的最长字串3 {
    public static void main(String[] args) {
        Solution solution = new 无重复字符的最长字串3().new Solution();
        int result = solution.lengthOfLongestSubstring("abba");
        System.out.println(result);
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int result = 0;
            int max=0;
            String temp = "";
            int is = 0;
            for (int i = 0; i < s.length()-1; i++) {
                temp = temp.concat(s.substring(i, i + 1));
                max = temp.length();
                String substring = s.substring(i + 1, i + 2);
                if (temp.contains(substring)) {
                    {
                        result = Math.max(max, result);
                        int i1 = temp.lastIndexOf(substring);
                        temp = temp.substring(i1 + 1, temp.length());

                    }
                    is = 1;
                }else {
                    if (i==s.length()-2) {
                        max += 1;
                    }
                }
            }
            result = Math.max(max, result);
            if (is == 0)
                return s.length();
            return result;
        }
    }
}
