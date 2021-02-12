import java.util.Scanner;

/* 
 문제메모
 	1. 농장 크기 항상 홀수 2. 크기에 딱 맞는 정사각형 마름모 형태
 	농장의 크기 N, 농작물 가치 주어질 때 얻을 수 있는 수익
 	농장의 크기 1<=N<=49  /  농작물 가치 0~5

 * */

// 마름모꼴 사방 배열 범위 기억해라~!

public class SWEA_2805_농작물_수확하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt(); // 농작물 크기
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				for (int j = 0; j < N; j++) {
					int num = s.charAt(j) - '0'; // 숫자 값 얻을 수 있다!!
					
					if(N/2 <= i+j 
							&& i+j <= N/2*3 
							&& -N/2 <= i-j 
							&& i-j <= N/2) {
						// 대각선 4방 안에 있는 범위
						sum += num;
					}
				}
			}
			System.out.println(sum);
		}
	}
} // end of class