import java.util.*;

/*
445. Add Two Numbers II

* You are given two non-empty linked lists representing two non-negative integers.
* The most significant digit comes first and each of their nodes contains a single digit.
* Add the two numbers and return the sum as a linked list.
* You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* */

// stack 이용한 솔루션 O(n)
public class assessment_0617_2_clean_ver {
    public static void main(String[] args) {
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

        int sum = 0;
        ListNode ans = new ListNode();

        while(!s1.isEmpty() || !s2.isEmpty()) {
            if(!s1.isEmpty()) sum += s1.pop();
            if(!s2.isEmpty()) sum += s2.pop();

            ans.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = ans;
            ans = head;
            sum /= 10;
        }

        return ans.val == 0? ans.next : ans;
    }

    public static Stack makeStack(ListNode cur) {
        Stack<Integer> st = new Stack<>();
        while(cur != null) {
            st.push(cur.val);
            cur = cur.next;
        }
        return st;
    }

}
