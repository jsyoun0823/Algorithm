package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 4
        int K = Integer.parseInt(st.nextToken()); // 2

        int len = N - K;
        String num = br.readLine();
        Stack<Character> stack = new Stack<>();

        // 1 9 2 4
        for (int i = 0; i < N; i++) {
            char cur = num.charAt(i);

            while(!stack.isEmpty() && stack.peek() < cur && K > 0) {
                stack.pop();
                K--;
            }

            stack.push(cur);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(stack.get(i));
        }

        System.out.println(sb);
    }
}
