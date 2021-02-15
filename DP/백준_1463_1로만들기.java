import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
 * 백준 1463. 1로 만들기
 * DP 문제
 * 
 * */
public class 백준_1463_1로만들기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1<=N<=1000000
		int[] table = new int[N + 1]; // 0번 방 안씀
		
		int min;
		for (int i = 2; i <= N; i++) {
			min = table[i - 1] + 1; // -1 연산
			if (i % 3 == 0 && table[i / 3] + 1 < min) min = table[i / 3] + 1;
			if (i % 2 == 0 && table[i / 2] + 1 < min) min = table[i / 2] + 1;
			table[i] = min;
		}
		System.out.println(table[N]);
	} // end of main
} // end of class
