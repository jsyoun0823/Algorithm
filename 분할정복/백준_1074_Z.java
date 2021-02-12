import java.io.IOException;
import java.util.Scanner;

/** 
 * 백준 1074. Z
 * 분할 정복
 * */

public class 백준_1074_Z {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 1 <= N <= 15
		int r = sc.nextInt(); 
		int c = sc.nextInt();
		
		int rs = 0, re = 1<<N; // 2^N
		int cs = 0, ce = 1<<N; // 2^N
		int sum = 0;
		
		while(true) {
			int rh = (re + rs) / 2; // 중간 지점
			int ch = (ce + cs) / 2; // 중간 지점
			int temp = (re - rs) / 2; // 4분할 한 영역의 한 변의 길이
			int size = temp * temp;
			if(rs <= r && r < rh && cs <= c && c < ch) { // 0번 영역 (왼쪽 위)
				re = rh; ce = ch;
			} else if (rs <= r && r < rh && ch <= c && c < ce) { // 1번 영역 (오른쪽 위)
				sum += size;
				re = rh; cs = ch;
			} else if(rh <= r && r < re && cs <= c && c < ch) { // 2번 영역 
				sum += size * 2;
				rs = rh; ce = ch;
			} else if (rh <= r && r < re && ch <= c && c < ce) { // 3번 영역
				sum += size * 3;
				rs = rh; cs = ch;
			} 
			if(size == 1) break;
		}
		System.out.println(sum);
	} // end of main
} // end of class
