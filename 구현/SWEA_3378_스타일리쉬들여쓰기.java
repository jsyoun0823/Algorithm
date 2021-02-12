import java.util.Arrays;
import java.util.Scanner;

public class SWEA_3378_스타일리쉬들여쓰기 {

	private static int p, q;
	private static String[] master;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			p = sc.nextInt(); // 마스터의 문장 라인 수
			q = sc.nextInt(); // 내 문장 라인 수
			master = new String[p];
			String[] me = new String[q];
			for (int i = 0; i < p; i++) {
				master[i] = sc.next();
			}
			for (int i = 0; i < q; i++) {
				me[i] = sc.next();
			}
			// 내 문장에서 각 라인별로 몇칸이 들여써져야 되는지 정답을 저장할 배열
			int[] result = new int[q]; // 0이상의 수는 구해진 들여쓰기의 수. -1은 유일하지 않은 경우
			// 아직 안구한 상태라는걸 구분하려면 -1과 0 이상의 수가 아닌 다른 값을 넣어우고 그 값을 아직 안구한걸로 약속
			Arrays.fill(result, -2); // -2가 들어있으면 아직 구하지 않은 걸로 약속~!
			
			for (int r = 1; r <= 20; r++) {
				for (int c = 1; c <= 20; c++) {
					for (int s = 1; s <= 20; s++) {
						// 모든 r,c,s 경우에 대해서
						if(isOk(master, r, c, s)) { // r, c, s가 마스터의 문장 들여쓰기에 적합하다면
							// 여기를 두번 이상 올 수도 있음
							// 내 문장에서의 각 라인별 들여쓰기를 구한다
							calIndent(me, r, c, s, result);
						}
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ');
			for (int i = 0; i < q; i++) {
				sb.append(result[i]).append(' ');
			}
			System.out.println(sb.toString());
		}
	}
	
	static void calIndent(String[]text, int r, int c, int s, int[] result) {
		int rCnt = 0, cCnt = 0, sCnt = 0; // 얘도 지금까지 열린 괄호의 갯수들로 저장할 변수 준비
		for (int i = 0; i < text.length; i++) {
			if(result[i] == -2) { // 해당 문장에 대해서 계산이 처음이라면
				result[i] = r * rCnt + s * sCnt + c * cCnt;
			} else { // 이미 구한적 있으면
				// 새로 구할 result 값이 이미 구한 result[i]와 다르다면 유일하지 않은 것
				if(result[i] != r * rCnt + s * sCnt + c * cCnt) 
					result[i] = -1;
			}
			for (char ch : text[i].toCharArray()) {
				switch (ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
		
	}
	static boolean isOk(String[] text, int r, int c, int s) {
		// 소괄호 중괄호 대괄호가 열려 있는 갯수를 세어가면서
		// 각 라인별로 문장 시작의 . 의 개수를 세서
		// 현재 소괄호 중괄호 대괄호 열린 상태와 r,c,s에 의해서 문장의 들여쓰기가 적합한지 검사
		int rCnt = 0, cCnt = 0, sCnt = 0; // 각 소 중 대 괄호의 현재 열려있는 갯수를 저장할 변수들
		for (int i = 0; i < text.length; i++) {
			// 해당 라인의 들여써진 갯수를 세자
			int cnt = 0;
			for (char ch : text[i].toCharArray()) {
				if(ch == '.') cnt++;
				else break;
			}
			
			// 들여쓰기 된 갯수가 현재 r,c,s와 소중대괄호 열린갯수에 맞지 않으면 r,c,s는 글러먹은 것
			if(cnt!=r*rCnt + c*cCnt+ s*sCnt)
				return false;
			
			//현재 문장을 쭉 읽으면서 소 중 대 괄호 열림 갯수를 갱신
			for (char ch : text[i].toCharArray()) {
				switch (ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
		return true;
	}

}
