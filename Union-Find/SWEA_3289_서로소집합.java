import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {
	private static int N;
	private static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append('#').append(test_case).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 집합 수, 1<=n<=1000000
			int M = Integer.parseInt(st.nextToken()); // 연산 개수, 1<=m<=100000
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				makeSet(i);
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int exp = Integer.parseInt(st.nextToken()); // 0: 합집합, 1: 같은 집합인지 확인
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(exp == 0) { // 합집합 연산 수행
					union(a, b);
				} else { // 같은 집합에 속해 있다면 1, 아니면 0 출력
					if(findSet(a) == findSet(b)) { 
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append('\n');
		} // end of for test case
		System.out.println(sb);
	} // end of main 
	
	/** 합집합 연산 */
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

	/** 대표자 찾기 */
	public static int findSet(int a) {
		if (parents[a] != a) {
			parents[a] = findSet(parents[a]);
		}
		return parents[a];
	}

	public static void makeSet(int a) {
		parents[a] = a;
	}

}
