import java.util.HashSet;
import java.util.Set;

public class 프로그래머스_완탐_소수찾기 {
	
	static int ans, R;
	static int[] trr, nums;
	static boolean[] select; // 사용한 숫자 체크
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) {
		String numbers = "011";
		int size = numbers.length();
		
		nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = numbers.charAt(i) - '0';
		}
		
		for (int i = 1; i <= size; i++) {
			trr = new int[i];
			select = new boolean[size];
			R = i;
			perm(0);
		}
		
		System.out.println(set.size());
	}
	

	/** 순열 뽑는 메소드 */
	public static void perm(int cnt) {
		if (cnt == R) { // 종료파트
			String str = "";
			for (int i = 0; i < trr.length; i++) {
				str += trr[i];
			}
			
			int temp = Integer.parseInt(str);
			if(isPrime(temp)) set.add(temp);
			
			cnt++;
			return;
		}
		
		for (int i = 0; i < select.length; i++) {
			if (!select[i]) { // 사용하지 않은 숫자라면 K단계 숫자로 사용해봄
				select[i] = true;
				trr[cnt] = nums[i];
				perm(cnt + 1);
				select[i] = false; // k번째 자리에 다른 숫자도 넣어보기 위해서 원복
			}
		}
	}
	
	/** 소수 판별하는 메소드 */
	// 기본적으로 소수를 판별하는 방법은 어느 수까지 판별하는 수를 나눠서 나머지가 0이 안나오면 소수로 인정
	// 해당 숫자의 √N 까지 확인하는 방법
	// 약수들의 곱으로 봤을때 루트를 씌운 값이 중간값이 된다.
	// 이 원리를 이용하여 2에서부터 √N의 값까지 검색
	public static boolean isPrime(int num) {
		if (num < 2)
			return false;

		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}

		return true;
	}

}
