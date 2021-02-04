import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조합적 문제.
 */
public class SWEA_1952_수영장{

	public static int moneys[], days[], min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 이용권 가격들
			moneys = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				moneys[i] = Integer.parseInt(st.nextToken());
			}

			// 12개월 이용 계획
			days = new int[12];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}

			plan();
			System.out.println("#" + t + " " + min);
		}

	}

	private static void plan() {
		min = moneys[3]; // 1년 사용권 금액을 기본 최소값
		calc(0, 0);
	}

	/** 매달의 이용가능한 경우를 고려하여 계산하는 메소드 */
	private static void calc(int m, int sum) {
		if (m >= 12) {
			if (min > sum) min = sum;
			return;
		}
		
		// 1일권 사용하는 경우 : 사용일수 * 1일권 금액
		calc(m + 1, sum + days[m] * moneys[0]);

		// 1달권 사용하는 경우 : 사용일수가 1일이상일 때 1달권 금액
		calc(m + 1, sum + (days[m] > 0 ? moneys[1] : 0));
		
		// 3달권 사용하는 경우
		if (days[m] > 0) {
			// 사용하면 3달의 이용일수가 3달 사용권으로 처리되므로 다음달이 아닌 3달 뒤로 넘어감.
			calc(m+3, sum + moneys[2]);
		} else { // 이용일수가 없는 경우
			calc(m+1, sum);
		}
	}

}
