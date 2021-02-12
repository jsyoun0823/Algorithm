import java.util.Scanner;

public class 정올_2604_그릇 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		int height = 0; // 그릇의 높이를 저장
		char pre = ' ';	// 이전 값의 상태를 저장
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i); // 한 문자씩 받아서 메모이제이션
			if (pre == c) {	// 이전값과 비교해서 같다면, 5 증가
				height += 5;
			} else { // 이전값과 비교해서 다르다면, 10 증가
				height += 10;
				pre = c;
			}
		}
		System.out.println(height);
	} // end of main
} // end of class
