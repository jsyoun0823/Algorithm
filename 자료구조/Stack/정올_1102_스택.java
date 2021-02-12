package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 정올_1102_스택 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Stack<Integer> st = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = br.readLine().trim();
			if(!str.contains("i")) {
				if(str.equals("c")) {
					sb.append(st.size()).append('\n');
				} else {
					if(st.size() == 0) {
						sb.append("empty").append('\n');
					} else {
						sb.append(st.pop()).append('\n');
					}
				}
			} else {
				StringTokenizer token = new StringTokenizer(str, " ");
				token.nextToken();
				st.push(Integer.parseInt(token.nextToken()));
			}
		}
		System.out.println(sb);
	}
}
