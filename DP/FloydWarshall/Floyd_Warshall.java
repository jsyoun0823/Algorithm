package FloydWarshall;

/*
  플로이드 워셜 알고리즘
    그래프에서 모든 꼭짓점 사이의 최단 경로의 거리를 구하는 알고리즘
    거쳐가는 노드를 하나씩 설정하여 반복문의 중심에 있게 해 문제를 해결한다

    제일 바깥쪽 반복문 = 거쳐가는 꼭짓점
    두 번째 반복문 = 출발하는 꼭짓점
    세 번째 반복문 = 도착하는 꼭짓점점

1 비용을 이차원 배열의 형태로 출력하면 다음과 같다 [현재까지 계산된 최소 비용]
   1->3으로 가는 경우 존재하지 않으므로 INF, 자기 자신은 0

     1   2   3   4
  1  0   5  INF  8
  2  7   0   9  INF
  3  2  INF  0  INF
  4 INF INF  3   0

  이 이차원 배열을 반족적으로 갱신해 최종적으로 모든 최소 비용을 구한다.
  이러한 반복의 기준이 '거쳐가는 점'

2) 노드 1을 거쳐가는 경우
    (2->3) 9 VS (2->1) + (1->3) = 7 + INF

3) 노드 2 거쳐가는 경우 => 4) 노드 3 거쳐가는 경우 .... 쭉쭉쭉


*/
public class Floyd_Warshall {

    static int number = 4;
    final static int INF = 10000000;

    static int [][] a = {
            {0, 5, INF, 8},
            {7, 0, 9, INF},
            {2, INF, 0, 4},
            {INF, INF, 3, 0}
    };

    public static void floydWarshall() {
        
        // 결과 그래프를 초기화
        int [][] d = new int[number][number];

        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                d[i][j] = a[i][j];
            }
        }
        
        // k = 거쳐가는 노드
        for (int k = 0; k < number; k++) {
            // i = 출발 노드
            for (int i = 0; i < number; i++) {
                // j = 도착 노드
                for (int j = 0; j < number; j++) {
                    // k를 거쳐갈 때가 더 적은 비용이면 갱신
                    if(d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }

        // 결과 출력
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        floydWarshall();
    }
}
