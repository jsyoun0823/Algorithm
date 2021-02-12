import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** 
 * 상점의 위치
 * 첫째 수 : 상점이 위치한 방향 (1:북, 2:남, 3:서, 4:동)
 * 둘째 수 : 북,남 => 왼쪽 경계로부터의 거리 (열)
 * 		  동, 서 => 위쪽 경계로부터의 거리 (행)
 * */
public class 백준_2564_경비원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken()); // 블록의 가로 길이 (열)
		int N = Integer.parseInt(st.nextToken()); // 블록의 세로 길이 (행)
		int S = Integer.parseInt(br.readLine()); // 상점의 개수
		
		int[][] stores = new int [S][3]; // 상점의  행,열,방향 저장
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			stores[i][2] = dir;
			switch (dir) {
			case 1: // 북
				stores[i][0] = 0;
				stores[i][1] = dis;
				break;
			case 2: // 남
				stores[i][0] = N;
				stores[i][1] = dis;
				break;
			case 3: // 서
				stores[i][0] = dis;
				stores[i][1] = 0;
				break;
			case 4: // 동
				stores[i][0] = dis;
				stores[i][1] = N;
				break;
			}
		}
		
		int dr = 0, dc = 0, dd = 0;
		st = new StringTokenizer(br.readLine(), " ");
		int dDir = Integer.parseInt(st.nextToken());
		int dDis = Integer.parseInt(st.nextToken());
		switch (dDir) {
		case 1:
			dc = dDis;
			dd = 2;
			break;
		case 2:
			dr = N;
			dc = dDis;
			dd = 1;
			break;
		case 3:
			dr = dDis;
			dd = 4;
			break;
		case 4:
			dr = dDis;
			dc = N;
			dd = 3;
			break;
		}

		int answer = 0;
		for (int i = 0; i < stores.length; i++) {
			int sr = stores[i][0]; // 상점 행
			int sc = stores[i][1]; // 상점 열
			int sd = stores[i][2]; // 상점 방향
			
			if(sd == dd) { // 동근이랑 상점이랑 마주보고 있다면
				if(sd == 1 || sd == 2) { // 북, 남인 경우
					answer += Math.min(dr + sr + M-dc + M-sc, dc + sc + dr + sr);
				} else { // 동, 서 인경우
					answer += Math.min(dc + sc + N-dr + N-sr, dc + sc + dr + sd);
				}
			} else { // 마주보고 있지 않다면
				answer += Math.abs(dr - sr) + Math.abs(dc - sc);
			}
		}
		System.out.println(answer);
	}

}
