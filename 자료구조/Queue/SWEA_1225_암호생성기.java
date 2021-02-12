package Queue;

import java.util.LinkedList;
import java.util.Scanner;

public class SWEA_1225_암호생성기 {

	public static Scanner sc = new Scanner(System.in);
	public static LinkedList<Integer> queue = new LinkedList<Integer>();
	public static void main(String[] args) {
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			queue.clear(); // 테스트 케이스마다 큐 초기화
			
			int T = sc.nextInt();
			for (int j = 0; j < 8; j++) {
				queue.add(sc.nextInt()); // 큐에 하나씩 저장
			}
			
			int num = 1;
			while(true) {
				int k = queue.poll();
				k = k - num;
				if(k <= 0) {
					queue.add(0);
					break;
				} 
				num++;
				queue.add(k);
				
				if(num == 6) num = 1;
			}
			
			System.out.print("#" + T + " ");
			for (int i = 0; i < 8; i++) {
				System.out.print(queue.poll() + " ");
			}
			System.out.println();
			
		} // end of test case
	} // end of main
} // end of class
