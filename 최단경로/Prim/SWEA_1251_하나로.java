package Prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1251. [S/W 문제해결 응용] 4일차 - 하나로
 * 최소신장트리 문제
 *  */
// 섬의 위치 정보 => 그래프 표현
// 무향 그래프
// N개 정점 : N-1개. N*(N-1)/2
// 간선리스트

// 프림 : 임의의 정점을 시작점으로 하여 시작
// 신장트리에 표함된 정점과 자신을 연결하는 간선 비용 최소값
public class SWEA_1251_하나로 {

	private static int N;
	private static long[][] adjMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[] x = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			} // N개 섬의 x좌표
			
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			} // N개 섬의 y좌표
			
			adjMatrix = new long[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i], x[j], y[i], y[j]);
				}
			} // 인접행렬 완성
			
			double E = Double.parseDouble(br.readLine());
			sb.append('#').append(t).append(' ').append(Math.round(E*makeMST())).append('\n');
		}
		System.out.println(sb);
	} // end of main

	private static long makeMST() { // 프림 알고리즘
		
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		
		long result = 0; // 최소신장트리비용
		int cnt = 0; // 처리한 정점수
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0; // 0점을 시작점으로
		
		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
		pQueue.offer(new Vertex(0, minEdge[0]));
		
		while(true) {
			// 1단계 : 신장트리에 포함되지 않은 정점중 최소간선비용의 정점 선택
			Vertex minVertex = pQueue.poll();
			
			if(visited[minVertex.no]) continue; 
			
			visited[minVertex.no] = true; // 정점 방문 처리(신장트리에 포함시킴)
			result += minVertex.cost; // 간선비용 누적
			if(++cnt == N) break;
			
			// 2단계 : 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선의 비용을 고려하여 minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minVertex.no][i] > 0 && minEdge[i] > adjMatrix[minVertex.no][i]) {
					minEdge[i] = adjMatrix[minVertex.no][i];
					pQueue.offer(new Vertex(i, minEdge[i]));
				}
			}
		}
		return result;
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		return (long)(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no;
		long cost;
		public Vertex(int no, long cost) {
			this.no = no;
			this.cost = cost;
		}
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.cost, o.cost);
		}
	}

} // end of class