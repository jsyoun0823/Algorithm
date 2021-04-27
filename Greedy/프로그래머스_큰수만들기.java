import java.util.*;

public class 프로그래머스_큰수만들기 {

	public static void main(String[] args) {
		String number = "4177252841" ;
		int k = 4;

		StringBuilder sb = new StringBuilder();

		int len = number.length() - k;

		Deque<Character> dq = new ArrayDeque<>();
		for(int i=0; i<number.length(); i++) {
			char cur = number.charAt(i);

			while(!dq.isEmpty() && dq.peekLast() < cur && k > 0) {
				dq.pollLast();
				k--;
			}

			dq.offer(cur);
		}

		for(int i=0; i<len; i++) {
			sb.append(dq.poll());
		}

		System.out.println(sb);
	}
}
