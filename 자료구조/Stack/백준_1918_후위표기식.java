package Stack;

import java.util.Scanner;
import java.util.Stack;

public class 백준_1918_후위표기식 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String exp = sc.next();

        Stack<Character> op = new Stack();

        String str = "";
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c >= 65 && c <= 90) { // 대문자 알파벳이라면
                str += c; // 문자열에 그대로 추가
            } else if (c == '*' || c == '/' || c == '+' || c == '-') { // 연산자라면
                if(!op.isEmpty()) { // 연산자 스택 비었는지 체크
                    while(!op.isEmpty() && priority(op.peek()) >= priority(c)) { // 연산자 우선순위 체크
                        str += op.pop(); // 나보다 높거나 같다면 pop 해서 문자열에 추가
                    }
                }
                op.push(c); // 현재 연산자 push
            } else if (c == '(') { // ( 면 그대로 push
                op.push(c);
            } else if (c == ')') { // ) 면
                while(!op.isEmpty() && op.peek() != '(') { // (가 나올 때까지
                    str += op.pop(); // 괄호 안에 있는 애들 문자열에 추가
                }
                op.pop(); // ( 는 pop해서 날리기
            }
        }

        while(!op.isEmpty()) { // 남아있는 연산자들 그대로 추가해주기
            str += op.pop();
        }

        System.out.println(str);

    }

    /** 연산자의 우선순위를 반환하는 메소드 */
    public static int priority(char oper) {
        int p = 1;

        if(oper == '*' || oper == '/') p = 3;
        else if (oper == '+' || oper == '-') p = 2;

        return p;
    }
}
