import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 
 * 정올 1863 종교
 * DisjoinSet 이용
 * */
public class 정올_1863_종교 {

	private static int N;
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수 0<=N<=50000, (정점)
		int M = Integer.parseInt(st.nextToken()); // 같은 종교를 가진 학생 쌍 수, (간선 수)
		parents = new int[N + 1];

		// makeSet (학생 번호 1<= i, j <= n)
		for (int i = 1; i <= N; i++) {
			makeSet(i);
		}
		
		// 간선 수 만큼 union 작업
		for (int edge = 0; edge < M; edge++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			union(i, j);
		}
		// 종교 수 출력
		int religion = 0;
		for (int i = 1; i <= N; i++) {
			if(i == parents[i]) religion++;
		}
		System.out.println(religion);
	} // end of main

	/** 두 집합을 합치기 */
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) { // 같은 그룹이면
			return false;
		}
		parents[bRoot] = aRoot; // 두 그룹을 합치기
		return true;
	}

	/** 대표자 찾기 */
	public static int findSet(int a) {
		// path compression
		if (parents[a] != a) {
			parents[a] = findSet(parents[a]);
		}
		return parents[a];
	}

	public static void makeSet(int a) {
		parents[a] = a; // 대표자를 표시
	}
}
