import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 백준 11005. 진법 변환2
 * 분류 : 구현
 * 10진수 수 N, B 진법으로 바꿀때
 * N을 B로 나누고 몫을 다시 B로 나누고,,, 나머지를 뒤에서부터 쓴다
 * 따라서 나누면서 바로바로 출력하면됨
 * 재귀 이용해서 풀이
 * 
 * */
public class 백준_11005_진법변환2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		change(N, B);
	}

	public static void change(int N, int B) {
		if (N != 0) {
			change(N / B, B);
			
			int temp = N % B;
			if (temp >= 10 && B > 10) {
				System.out.print((char)('A' + (temp - 10)));
			} else {
				System.out.print(temp);
			}
		}
	}

}
