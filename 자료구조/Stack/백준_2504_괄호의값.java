package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준_2504_괄호의값 {

	private static Stack<Character> st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[] arr = str.toCharArray();
		
		st = new Stack<Character>();
		int ans = 0;
		
		for (char c : arr) {
			if(c == '(' || c == '[') {
				st.push(c);
			} else {
				char top = (char) st.peek();
				if(c == ')') {
					if(top == '(') {
						st.pop();
						st.push('2');
					} else { // 숫자이거나 짝이 안맞는 괄호
						ans = check('(');
					}
				} else { // ] 인 경우
					if(top == '[') {
						st.pop();
						st.push('3');
					} else {
						ans = check('[');
					}
				}
			}
		}
		System.out.println(st.pop()-'0');
	}

	public static int check(char c) {
		int result = 0;
		while(!st.isEmpty()) {
			char top = (char) st.peek();
			
			if(Character.isDigit(top)) { // 탑이 숫자라면
				result += (st.pop() - '0'); // result에 저장
			} else if (top == c) {
				if(top == '(') result *= 2;
				else result *= 3;
				st.pop();
				st.push((char) result);
				break;
			} else {
				return -1;
			}
		}
		return result;
	}
}
