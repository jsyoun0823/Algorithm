import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 4
        int K = Integer.parseInt(st.nextToken()); // 2

        int len = N - K;
        // 1 9 2 4  // 94

        String line = br.readLine();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            char cur = line.charAt(i);

            while(!deque.isEmpty() && deque.peekLast() < cur && K > 0) {
                deque.pollLast();
                K--;
            }

            deque.offer(cur);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(deque.poll());
        }

        System.out.println(sb);
    }
}
