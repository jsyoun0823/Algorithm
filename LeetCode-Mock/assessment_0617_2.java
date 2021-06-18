import java.util.*;

/*
445. Add Two Numbers II

* You are given two non-empty linked lists representing two non-negative integers.
* The most significant digit comes first and each of their nodes contains a single digit.
* Add the two numbers and return the sum as a linked list.
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* */

public class assessment_0617_2 {
    public static void main(String[] args) {
//        ListNode l1 = makeTestCase(new int[] {7, 2, 4, 3});
//        ListNode l2 = makeTestCase(new int[] {5, 6, 4});

        ListNode l1 = makeTestCase(new int[] {2, 4, 3});
        ListNode l2 = makeTestCase(new int[] {5, 6, 4});

        ListNode ans = addTwoNumbers(l1, l2);

        while(ans != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
        System.out.println();
        // l1 = 7, 2, 4, 3
        // l2 = 5, 6, 4
        // output : 7, 8, 0, 7
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Stack<Integer> s1 = makeStack(l1);
        Stack<Integer> s2 = makeStack(l2);

        boolean flag = false; // 전 연산에서 올림수가 있었는지
        Stack<Integer> sum = new Stack<>();

        int diff = s1.size() - s2.size();

        while(!s1.isEmpty() && !s2.isEmpty()) {
            int x = s1.pop();
            int y = s2.pop();

            int plus = x + y;
            if(flag) {
                plus++;
            }

            if(plus >= 10) {
                sum.push((plus - 10));
                flag = true;
            } else {
                sum.push(plus);
                flag = false;
            }
        }

        if (diff > 0) {
            while(!s1.isEmpty()) {
                int num = s1.pop();
                if(flag) num++;
                if(num >= 10) {
                    sum.push(num - 10);
                    flag = true;
                } else {
                    sum.push(num);
                    flag = false;
                }
            }
        } else if (diff < 0) {
            while(!s2.isEmpty()) {
                int num = s2.pop();
                if(flag) num++;
                if(num >= 10) {
                    sum.push(num - 10);
                    flag = true;
                } else {
                    sum.push(num);
                    flag = false;
                }
            }
        }

        if(flag) sum.push(1);


        return makeList(sum);
    }

    private static ListNode makeList(Stack<Integer> sum) {
        ListNode prev = new ListNode();
        ListNode root = prev;
        while(!sum.isEmpty()) {
            ListNode cur = new ListNode(sum.pop(), null);
            prev.next = cur;
            prev = cur;
        }

        return root.next;
    }

    public static Stack makeStack(ListNode cur) {
        Stack<Integer> st = new Stack<>();
        while(cur != null) {
            st.push(cur.val);
            cur = cur.next;
        }
        return st;
    }

    private static ListNode makeTestCase(int[] arr) {
        ListNode prev = new ListNode();
        ListNode root = prev;

        for (int i = 0; i < arr.length; i++) {
            ListNode cur = new ListNode(arr[i], null);
            prev.next = cur;
            prev = cur;
        }

        return root.next;
    }

}
