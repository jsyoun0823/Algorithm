import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_15686_치킨배달 {
    private static int N, M, min;
    private static int[][] map;
    private static List<int[]> chicken, house;
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 크기 (2 ≤ N ≤ 50)
        M = Integer.parseInt(st.nextToken()); // 폐업시키지 않을 치킨집의 최대 개수 (1 ≤ M ≤ 13)

        map = new int[N][N]; // NxN 크기 도시의 정보
        chicken = new ArrayList<>(); // 치킨집 좌표 담을 리스트
        house = new ArrayList<>(); // 집의 좌표 담을 리스트

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0은 빈 칸, 1은 집, 2는 치킨집

                if(map[i][j] == 2) chicken.add(new int[] {i, j});
                else if(map[i][j] == 1) house.add(new int[] {i, j});
            }
        }

        min = Integer.MAX_VALUE; // 도시의 치킨 거리 최솟값
        selected = new boolean[chicken.size()]; // 치킨 선택할 boolean 배열
        comb(0, 0);

        System.out.println(min);
    }

    /** M개의 치킨집을 골라 최소 치킨 거리를 찾는 함수 */
    private static void comb(int start, int cnt) {
        if(cnt == M) { // M개를 모두 골랐다면
            find_min(); // 최소값 찾는다
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            selected[i] = true;
            comb(i + 1, cnt + 1);
            selected[i] = false;
        }
    }

    /** 각 집으로부터 선택된 치킨집과의 모든 거리를 비교해 최소 치킨 거리 찾는 함수 */
    private static void find_min() {
        int sum = 0; // 각 집으로부터 선택된 치킨집과의 모든 거리를 구해 비교

        for (int i = 0; i < house.size(); i++) {
            int[] cur_house = house.get(i); // 현재 집 좌표 배열
            int dist = Integer.MAX_VALUE; // 집과 가장 가까운 치킨집 사이의 거리

            for (int j = 0; j < chicken.size(); j++) { // 선택된 치킨집 좌표 꺼내서 거리 계산
                if(!selected[j]) continue;

                int[] cur_chicken = chicken.get(j);
                int temp_d = get_dist(cur_house[0], cur_house[1], cur_chicken[0], cur_chicken[1]); // 집과 치킨집 사이 거리 계산

                dist = Math.min(dist, temp_d); // 가장 가까운 거리 갱신 (최소값 갱신)
            }

            sum += dist; // 치킨 거리 합 누적
        }

        min = Math.min(min, sum); // 도시의 치킨거리 최소값 갱신
    }

    /** 치킨 거리를 구하는 함수 : |r1-r2| + |c1-c2| */
    private static int get_dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

}
