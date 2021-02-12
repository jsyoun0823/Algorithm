package 알고리즘수업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643_키순서 {

	static int N, M, gtCnt, ltCnt;
	static int[][] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(bf.readLine()); // 학생 수 : 정점 수
			M = Integer.parseInt(bf.readLine()); // 관계 수 : 간선 수
			
			adj = new int[N+1][N+1];
			StringTokenizer st = null;
			
			int i, j;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(bf.readLine(), " ");
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				adj[i][j] = 1;
			}
			
			int answer = 0;
			for (int k = 1; k <= N; k++) {
				gtCnt = ltCnt = 0;
				gtDFS(k, new boolean[N+1]);
				ltDFS(k, new boolean[N+1]);
				if(gtCnt + ltCnt == N-1) answer++;
			}
			System.out.println("#" + t + " " + answer);
		}
	}

	// 자신보다 큰 학생 따라 탐색
	public static void gtDFS(int k, boolean[] visited) { // 현재 기준이 되는 학생번호
		visited[k] = true;
		for (int i = 1; i <= N; i++) {
			if(adj[k][i] == 1 && !visited[i]) {
				gtCnt++;
				gtDFS(i, visited);
			}
		}
	}
	
	// 자신보다 작은 학생 따라 탐색
	public static void ltDFS(int k, boolean[] visited) { 
		visited[k] = true;
		for (int i = 1; i <= N; i++) {
			if(adj[i][k] == 1 && !visited[i]) {
				ltCnt++;
				ltDFS(i, visited);
			}
		}
	}
}
