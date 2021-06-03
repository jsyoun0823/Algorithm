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

        int bombSize = bomb.length;
        char[] res = new char[str.length()];
        int resIdx = 0;
        for (int i = 0; i < str.length(); i++) {
            res[resIdx++] = str.charAt(i); // 결과 문자열에 c현재 문자 추가

            // idx == top,, 'C'
            // m i r k o v C '4'
            // 만약에 idx 지금 6이면 res[6] == bomb의 다음꺼 length - 2
            if(res[resIdx - 1] == bomb[bombSize - 1]) { // 폭발 문자 맨 뒤랑 같을 때

                if(resIdx < bombSize) continue; // 폭발 문자 사이즈 만큼은 확보 되어야 함

                boolean flag = false;

                // mirkovC4nizCC44
                // m i r k o v n i z C C
                // c == '4'

                // res[k]   bomb[j]     flag
                //  6, C       0, C       f
                // 폭발 문자열 존재하는거니까 resIdx -= bomb.length - 1 만큼 인덱스 앞으로 옮김
                // 5 ,, 그래서 지금 v

                // 폭발 문자열 길이 만큼 현재 resIdx 값과 폭발 문자 비교해서
                for (int j = 0; j < bombSize; j++) {
                    if(res[resIdx - 1 - j] != bomb[bombSize - 1 - j]) {
                        flag = true; // 하나라도 다르면 넘어간다
                        break;
                    }
                }

                if(!flag) { // 모두 같았으면
                    resIdx -= bombSize; // 그만큼 인덱스에서 빼준다
                }
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
