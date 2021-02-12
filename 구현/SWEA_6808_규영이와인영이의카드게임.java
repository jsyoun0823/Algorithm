import java.util.Scanner;

public class SWEA_6808_규영이와인영이의카드게임 {
	static int[] i_cards; // 인영이 카드
	static int[] g_cards; // 규영이 카드
	public static void main(String[] args) {
		// 1. 규영이가 받은 카드가 뭐뭐인지?
		// 2. 인영이의 9장 카드를 모든 순열 구성
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 1번부터 18번 카드 중에 규영이가 내는 카드는 인영이가 고를 수 없다
			// 1부터 18번 카드의 사용여부를 저장할 배열
			boolean[] cards = new boolean[19];
			check = new boolean[9];
			g_cards = new int[9];
			for (int i = 0; i < 9; i++) {
				g_cards[i] = sc.nextInt();
				cards[g_cards[i]] = true; // 규영이가 받은 카드번호를 체크
			}
			// 체크가 안된 번호는 인영이꺼 !
			i_cards = new int[9];
			// cards 배열에서 false 만날때마다 인영이 카드가 골라지는건데
			// 몇번 째 카드를 골랐는지를 저장할 인덱스
			int idx = 0; 
			for (int i = 1; i <= 18; i++) {
				if(!cards[i]) 
					i_cards[idx++] = i;
			}
			i_win = g_win = 0;
			perm(0); // 인영이 카드에 대해서 순열을 돌린다
			System.out.println("#" + tc + " " + g_win + " " + i_win);
		}
	}

	static int[] result = new int[9];
	static boolean[] check;
	static int g_win; 
	static int i_win;
	static void perm(int idx) {
		if(idx == 9) {
			int g_score = 0, i_score = 0;
			for (int i = 0; i < 9; i++) {
				if(g_cards[i] > result[i]) {
					g_score += (g_cards[i] + result[i]);
				} else {
					i_score += (g_cards[i] + result[i]);
				}
			}
			if(g_score > i_score) g_win++;
			else i_win++;
			return;
		}
		for (int i = 0; i < 9; i++) {
			if(!check[i]) {
				check[i] = true;
				result[idx] = i_cards[i];
				perm(idx + 1);
				check[i] = false;
			}
		}
	}

}
