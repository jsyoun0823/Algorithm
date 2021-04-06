package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준_4889_안정적인문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        while(true) {
            String line = br.readLine();
            if(line.contains("-")) break; // 입력의 마지막 줄은 '-'가 한 개 이상 주어진다.

            // 1) 스택 비어있을 때 '}'를 만나면 '{'로 바꿔줌
            int cnt = 0;
            Stack<Character> st = new Stack<>();
            char[] crr = line.toCharArray();
            for (char c : crr) {
                if(c == '{') st.push(c);
                else if(c == '}') { // {
                    if(st.isEmpty()) { // 스택이 비어있을 때
                        st.push('{'); // '}' -> '{'로 바꿔주고 스택에 저장
                        cnt++; // 연산 카운팅 증가
                    } else {
                        if(st.peek() == '{') st.pop();
                    }
                }
            }

            // 2) 순회 끝난 후에도 스택에 원소가 남아있다면
            // '{' 가 남아있는 경우 ex) {{ -> {} 이렇게 한번 바꾸면 되니까
            // 남아있는 '{' 갯수 세서 반으로 나눈다
            int temp = 0;
            while(!st.isEmpty()) {
                char c = st.pop();
                if(c == '{') temp++;
            }
            cnt += temp / 2;

            sb.append(tc).append('.').append(' ').append(cnt).append('\n');
            tc++;
        }
        System.out.println(sb);
    }
}
