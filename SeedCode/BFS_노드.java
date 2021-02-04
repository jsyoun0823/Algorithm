import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_노드 {
	
	static ArrayList<Integer> [] arr;
	static int N;
	
	public static void main(String[] args) {
		int[][] edge = {{3,6}, {4,3}, {3,2}, {1,3}, {1,2}, {2,4}, {5,2}};
        N = 6;
        
        arr = new ArrayList[N+2];
        
        for(int i=0; i<=N; i++) {
            arr[i] = new ArrayList<Integer>();
        }
        
        for(int[] e : edge) {
            arr[e[0]].add(e[1]);
            arr[e[1]].add(e[0]);
        }
        
        System.out.println(bfs());
	}
		
	public static int bfs() {
		
		Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        
        queue.offer(new int[] {1, 0});
        visited[1] = true;
        
        int max = -1;
        int ans = 0;
        
        while(!queue.isEmpty()) { // 반복 큐가 빌 때까지
            int[] cur = queue.poll(); // 큐에서 하나 꺼냄
            
            int idx = cur[0];
            int cnt = cur[1];
            
            if(max < cnt) {
            	max = cnt;
            	ans = 1;
            } else if(max == cnt) {
            	ans++;
            }
            
            for(int j=0; j<arr[idx].size(); j++) {
            	
                int temp = arr[idx].get(j);
                
                if(!visited[temp]) {
                    visited[temp] = true;
                    queue.offer(new int[] {temp, cnt+1});
                }
            }
        }
        return ans;
	}
	
}
