import java.util.Scanner;

//		KOI 찾아서 인덱스 반환, KOIcnt++;
//		KO 만 지워서 다시 저장 substring i, i+1 까지만 ! I남겨두기
//		IOI 찾아서  똑같이 

public class 정올_2514_문자열찾기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int kcnt = 0, icnt = 0;
		
		for (int i = 0; i < s.length()-2; i++) { // i : 3글자씩 읽을 시작위치
			String sub = s.substring(i, i + 3);
			if(("KOI").equals(sub)) kcnt++;
			if(("IOI").equals(sub)) icnt++;
		}

		System.out.println(kcnt + "\n" + icnt);
	} // end of main
} // end of class