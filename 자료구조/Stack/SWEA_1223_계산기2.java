package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/** 
 * SWEA 1223. 계산기2
 * Stack
 * */
/* 
  후위 표기식으로 바꾸어 계산하는 프로그램
  +, * 두 종류
  0 ~ 9 의 정수
  
  절차)
  1) 후위표기식으로 변환
  	숫자면 스택에 x 그대로 출력
  	연산자면 우선순위 체크해서 스택에 담거나 출력
  		스택이 비었으면 스택에 push
  		스택에 안비었으면 우선순위 체크(함수만들기)
  		 	->스택에 있는 연산자의 우선순위 같거나 크다면 스택에 있는 연산자 pop하고 출력한후 현재 연산자 스택에 push
  		우선순위가 현재 연산자가 더 크면 현재 연산자 push
  	수식이 끝나면 스택 빌때까지 pop
  	
  2) 후위표기식 계산하기
    숫자면 stack에 push
  + 연산자면 stack에 있는 두 개의 숫자 뽑아서 더하고, 결과값을 다시 stack에 push
  * 연산자면 stack에 있는 두 개의 숫자 뽑아서 곱하고, 결과값을 다시 stack에 push
 * */

public class SWEA_1223_계산기2 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			StringBuilder sb = new StringBuilder();
			int T = Integer.parseInt(br.readLine());
			Stack<Integer> result = new Stack<Integer>();
			Stack<Character> op = new Stack<Character>();
			
			// 1. 후위표기식 변환
			String str = "";
			String exp = br.readLine();
			for (int i = 0; i < T; i++) {
				char c = exp.charAt(i);
				if(c - '0' >= 0 && c - '0' < 10) { // 읽어들인 문자가 0-10 사이 정수라면
					str += c; // 그대로 출력 (문자열에 저장)
				} else { // 연산자라면
					if(!op.isEmpty()) { // 연산자 스택이 비지 않았고,
						if(priority(op.peek()) >= priority(c)) {  // 스택에 있는 연산자의 우선순위 같거나 크다면
							str += op.pop(); // 스택에 있는 연산자 pop 한 후,
							op.push(c); // 현재 연산자 스택에 push
						} else {
							op.push(c);
						}
					} else {
						op.push(c);
					}
				}
			}
			while (!op.isEmpty()) { // 수식이 끝나면 스택 빌때까지 연산자 pop
				str += op.pop();
			}
			
			// 2. 후위표기식 계산
			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				if(c - '0' >= 0 && c -'0' < 10) { //숫자면 stack에 push
					result.push(c - '0');
				} else {
					int num1 = result.pop();
					int num2 = result.pop();
					if(c == '*') { // * 연산자면 stack에 있는 두 개의 숫자 뽑아서 곱하고, 결과값을 다시 stack에 push
						result.push(num1 * num2);
					} else { // + 연산자면 stack에 있는 두 개의 숫자 뽑아서 더하고, 결과값을 다시 stack에 push
						result.push(num1 + num2);
					}
				}
			}
			sb.append('#').append(test_case).append(' ').append(result.pop());
			System.out.println(sb);
		} // end for test case
	} // end of main

    /** 연산자의 우선순위를 반환하는 메소드 */
	public static int priority(char oper) {
		int p = -1;
		if (oper == '*') {
			p = 3;
		} else if (oper == '+') {
			p = 1;
		}
		return p;
	}
} // end of class
