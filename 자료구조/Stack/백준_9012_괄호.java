package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준_9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            char[] crr = br.readLine().toCharArray();

            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            for (int j = 0; j < crr.length; j++) {
                if(crr[j] == '(') stack.push(crr[j]);
                else {
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    if(stack.peek() == '(') stack.pop();
                }
            }

            if(!flag || !stack.isEmpty()) sb.append("NO\n");
            else sb.append("YES\n");
        }

        System.out.println(sb);
    }
}
