package com.zj.springboottest.leetcocde;

 /**
 * @Author zhangjin
 * @Date 2024/2/27 13:26
 * @description:
 */
public class 两数相加2 {
    public static void main(String[] args) {
        ListNode l1=new ListNode(2,new ListNode(4,new ListNode(3)));
        ListNode l2=new ListNode(5,new ListNode(6,new ListNode(4)));
        Solution solution=new 两数相加2().new Solution();
        ListNode listNode = solution.addTwoNumbers(l1, l2);
        while(listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }

    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result;
            ListNode r=new ListNode(0);
            result=r;
            int n=0;
            int n1,n2,sum;
            while(l1!=null||l2!=null){
                n1=l1==null?0:l1.val;
                n2=l2==null?0:l2.val;
                sum=n1+n2+n;
                n=0;
                if(sum<10){
                    r.next=new ListNode(sum);
                }else{
                    n=1;
                    r.next=new ListNode(sum-10);
                }
                r=r.next;
                if(l1 != null) l1 = l1.next;
                if(l2 != null) l2 = l2.next;
            }
            if(n==1){
                r.next=new ListNode(1);
            }
            return result.next;
        }
    }
}




