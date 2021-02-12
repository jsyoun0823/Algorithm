import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6109_추억의2048게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 맵 크기 (1≤N≤20)
			String S = st.nextToken(); // 명령어
			int[][] map = new int[N+1][N+1];
			int[][] newMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				map[i][N] = 0;
			}
			/* ------ 입력 처리 ----- */
			switch (S) {
			case "up":
				for (int i = 0; i < N; i++) {
					int idx = 0;
					for (int j = 0; j < N; j++) {
						int cur = map[j][i];

						if (cur == 0) continue; // 현재값 0이면 다음으로 넘어감 
						
						if(cur == map[j+1][i]) { // 현재 값이 다음 값이랑 같은 경우
							newMap[idx][i] = cur + map[j+1][i]; // 합쳐서 새 맵에 저장
							map[j+1][i] = 0; // 다음 요소엔 0 저장
							idx++;
						} else { // 다른 경우
							int k = j + 1;
							boolean isChagne = false;
							while(k <= N-1) { // 0을 중간에 끼고 있는 경우
								if(map[k][i] != 0) break; // 중간에 0이 아니면 탈락
								
								if(cur == map[k+1][i]) {
									isChagne = true;
									newMap[idx][i] = cur * 2 ; // 합쳐서 저장
									map[k+1][i] = 0;
									idx++;
									break;
								}
								k++;
							}
							if(!isChagne) { // 끝까지 같은거 못찾았을 경우
								newMap[idx][i] = cur; // 그냥 지금 값 저장
								idx++;
							}
						}
						
					}
				}
				break;
			case "down":
				for (int i = 0; i < N; i++) {
					int idx = N-1;
					for (int j = N; j >= 0; j--) {
						int cur = map[j][i];

						if (cur == 0) continue; // 현재값 0이면 다음으로 넘어감 
						
						if(j == 0) {
							newMap[idx][i] = cur;
							break;
						}
						if(cur == map[j-1][i]) { // 현재 값이 다음 값이랑 같은 경우
							newMap[idx][i] = cur * 2; // 합쳐서 새 맵에 저장
							map[j-1][i] = 0; // 다음 요소엔 0 저장
							idx--;
						} else { // 다른 경우
							int k = j - 1;
							boolean isChagne = false;
							while(k > 0) { // 0을 중간에 끼고 있는 경우
								if(map[k][i] != 0) break; // 중간에 0이 아니면 탈락
								
								if(cur == map[k-1][i]) {
									isChagne = true;
									newMap[idx][i] = cur * 2 ; // 합쳐서 저장
									map[k-1][i] = 0;
									idx--;
									break;
								}
								k--;
							}
							if(!isChagne) { // 끝까지 같은거 못찾았을 경우
								newMap[idx][i] = cur; // 그냥 지금 값 저장
								idx--;
							}
						}
					}
				}
				break;
			case "left":
				for (int i = 0; i < N; i++) {
					int idx = 0;
					for (int j = 0; j < N; j++) {
						int cur = map[i][j];
						
						if (cur == 0) continue; // 현재값 0이면 다음으로 넘어감 
						
						if(cur == map[i][j+1]) { // 현재 값이 다음 값이랑 같은 경우
							newMap[i][idx] = cur * 2; // 합쳐서 새 맵에 저장
							map[i][j+1] = 0; // 다음 요소엔 0 저장
						} else { // 다른 경우
							int k = j + 1;
							boolean isChagne = false;
							while(k <= N-1) { // 0을 중간에 끼고 있는 경우
								if(map[i][k] != 0) break; // 중간에 0이 아니면 탈락
								
								if(cur == map[i][k+1]) {
									isChagne = true;
									newMap[i][idx] = cur * 2 ; // 합쳐서 저장
									map[i][k+1] = 0;
									idx++;
									break;
								}
								k++;
							}
							if(!isChagne) { // 끝까지 같은거 못찾았을 경우
								newMap[i][idx] = cur; // 그냥 지금 값 저장
								idx++;
							}
						}
					}
				}
				break;
			case "right":
				for (int i = 0; i < N; i++) {
					int idx = N-1;
					for (int j = N; j >= 0; j--) {
						int cur = map[i][j];
						
						if (cur == 0) continue; // 현재값 0이면 다음으로 넘어감 
						
						if(j == 0) {
							newMap[i][idx] = cur;
							break;
						}
						
						if(cur == map[i][j-1]) { // 현재 값이 다음 값이랑 같은 경우
							newMap[i][idx] = cur * 2; // 합쳐서 새 맵에 저장
							map[i][j-1] = 0; // 다음 요소엔 0 저장
							idx--;
						} else { // 다른 경우
							int k = j - 1;
							boolean isChagne = false;
							while(k > 0) { // 0을 중간에 끼고 있는 경우
								if(map[i][k] != 0) break; // 중간에 0이 아니면 탈락
								
								if(cur == map[i][k-1]) {
									isChagne = true;
									newMap[i][idx] = cur * 2 ; // 합쳐서 저장
									map[i][k-1] = 0;
									idx--;
									break;
								}
								k--;
							}
							if(!isChagne) { // 끝까지 같은거 못찾았을 경우
								newMap[i][idx] = cur; // 그냥 지금 값 저장
								idx--;
							}
						}
					}
				}
				break;
			}
			
			sb.append('#').append(t).append('\n');
			for (int[] is : newMap) {
				for (int i : is) {
					sb.append(i).append(' ');
				}
				sb.append('\n');
			}
		} // end of for test case
		System.out.println(sb);
	} // end of main
} // end of class
