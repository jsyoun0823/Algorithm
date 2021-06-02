import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class 백준_9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] bomb = br.readLine().toCharArray();
        char[] res = new char[str.length()];
        int resIdx = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // idx == top,, 'C'
            // m i r k o v C '4'
            // c == 4
            // 만약에 idx 지금 6이면 res[6] == bomb의 다음꺼 length - 2
            if(c == bomb[bomb.length - 1]) {
                boolean flag = false;

                // mirkovC4nizCC44
                // m i r k o v n i z C C
                // c == '4'

                // res[k]   bomb[j]     flag
                //  6, C       0, C       f
                // 폭발 문자열 존재하는거니까 resIdx -= bomb.length - 1 만큼 인덱스 앞으로 옮김
                // 5 ,, 그래서 지금 v임

                //
                for (int j = bomb.length - 2, k = resIdx; j >= 0; j--, k--) {
                    if(res[k] != bomb[j]) {
                        flag = true;
                        break;
                    }
                }

                if(flag) {
                    res[resIdx++] = c;
                } else {
                    resIdx -= (bomb.length - 1);
                }
            } else {
                res[resIdx++] = c;
            }
        }

        StringBuilder sb = new StringBuilder();
        if(resIdx == 0) {
            sb.append("FRULA");
        } else {
            for (int i = 0; i < resIdx; i++) {
                sb.append(res[i]);
            }
        }

        System.out.println(sb);

//
//        Stack<Character> stack = new Stack<>();
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//
//            // m i r k o v 'C'
//
//            // m i r k o v C '4'
//            // 뒤에서부터 체크
//            int idx = 1;
//            while(c == bomb[bomb.length - idx]) {
//                if(stack.size() < bomb.length) break;
//
//                // stack : m i r k o v C
//                // c : 4
//                // bomb : C4
//                idx++;
//                if(stack.peek() == bomb[bomb.length - idx]) {
//
//                }
//            }
//            stack.push(c);
//
//        }
    }
}
