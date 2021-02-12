import java.util.Scanner;

public class SWEA_3349_최솟값으로이동하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int w = sc.nextInt();
			int h = sc.nextInt();
			int N = sc.nextInt();
			int[] arrX = new int[N];
			int[] arrY = new int[N];
			for (int i = 0; i < N; i++) {
				arrX[i] = sc.nextInt();
				arrY[i] = sc.nextInt();
			}
			int ans = 0;
			for (int i = 0; i < N-1; i++) {
				ans += getDist(arrX[i], arrX[i+1], arrY[i], arrY[i+1]);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

// 좌상 - 우하 위치일 때 대각선 탈 일 없는 경우
// 좌하 - 우상일 때 대각선 탈 일 있는 경우
	
// (a, b) (c, d)
// if (대각?) { 얼마나 대각? / 얼마나 노대각선? }
// else { |a-c|+ |b+d| }
	
// ( a<c && b<d ) || ( a>c && b>d) => 한쪽이 둘다 큰 경우
// dx = a-c; dy = b-d;
// dx dy 둘 다 음수거나 둘 다 양수다
// dx * dy > 0 이라는 것 
	static int getDist(int x1, int x2, int y1, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		int dist = 0;
		if(dx * dy > 0) {
//			dist = 대각선 횟수 + 상하좌우 이동횟수
			// 행이나 열이 둘 중 하나라도 같아지면 더이상 대각선 탈 수 없다
			// 그러므로 대각선 횟수는 dx, dy중에 작은 값이다.
			
			// 상하좌우 이동횟수는 x좌표의 차이 - y좌표의 차이 절대값
			// 대각선 횟수 + 상하좌우 이동횟수
			dist = Math.min(Math.abs(dx), Math.abs(dy)) + 
					Math.abs(Math.abs(dx) - Math.abs(dy));
//		 == dist = Math.abs(dx) + Math.abs(dy) - Math.min(Math.abs(dx), Math.abs(dy));
			
		} else {
			dist = Math.abs(dx) + Math.abs(dy);
		}
		return dist;
	}
}
