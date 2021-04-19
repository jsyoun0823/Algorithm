package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1916_최소비용구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int INF = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		int[] cost = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[s][e] = c;
		}
		Arrays.fill(cost, INF);
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		cost[start] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->a[1]-b[1]);
		pq.offer(new int[] {start, cost[start]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int townNum = cur[0];
			int busCost = cur[1];
			
			if(cost[townNum] < busCost) continue;

			if(visited[townNum]) continue;
			visited[townNum] = true;
			

			System.out.printf("%d %d\n", townNum, busCost);
			
			
			for (int j = 1; j <= N; j++) {
				if(!visited[j]
					&& map[townNum][j] != 0
					&& cost[j] > busCost + map[townNum][j]) {
					cost[j] = busCost + map[townNum][j];
					pq.offer(new int[] {j, cost[j]});
				}
			}
			
		}
		System.out.println(cost[end]);
	}

}
