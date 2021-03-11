package Stack;

import java.util.Arrays;
import java.util.Stack;

public class 프로그래머스_주식가격 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};

        int len = prices.length;
        int[] answer = new int[len];
        Stack<Integer> st = new Stack<>();

        // 1 2 3 2 3
        // 0 1 2 3 4

        // i   0    1    2    3    4

        // 0(1) 1(2) 3(2) 4(3)
        // 0 0 1 0 0
        // 시간 순대로, 가격을 비교하며 stack에 index 넣기
        for(int i=0; i<len; i++) {
            while(!st.isEmpty() && prices[st.peek()] > prices[i]) {
                int top = st.pop();
                answer[top] = i - top;
            }
            st.push(i);
        }

        // 4 3 1 1 0
        // stack에 남아있는 원소들 빼면서 살아 남아있던 시간 저장
        while(!st.isEmpty()) {
            int cur = st.pop();
            answer[cur] = len - cur - 1;
        }

        System.out.println(Arrays.toString(answer));
    }
}
