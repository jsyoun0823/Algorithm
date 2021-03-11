import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/*
    [브루트포스, 백트래킹]
    전체 선생님의 수는 5이하의 자연수, 전체 학생의 수는 30이하의 자연수이며 항상 빈 칸의 개수는 3개 이상으로 주어진다.
    정확히 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지의 여부를 출력
*/
public class 백준_18428_감시피하기 {
    static int N;
    static char[][] map;
    static List<int[]> teacher, space;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 복도의 크기 (3 ≤ N ≤ 6)
        map = new char[N][N]; // NxN 크기의 복도 정보
        teacher = new LinkedList<>();
        space = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0, index = 0; j < N; j++, index+=2) {
                map[i][j] = str.charAt(index); // 학생이 있다면 S, 선생님이 있다면 T, 아무것도 존재하지 않는다면 X
                if(map[i][j] == 'T') teacher.add(new int[] {i, j}); // 선생님 위치 저장
                else if (map[i][j] == 'X') space.add(new int[] {i, j}); // 아무것도 없는 공간 위치 저장
            }
        }
        flag = false;
        set_obstacle(0, 0);

        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }

    public static int[] dr = {-1, 1, 0, 0}; // 상하좌우 4가지 방향
    public static int[] dc = {0, 0, -1, 1};

    public static void set_obstacle(int idx, int cnt) {
        if(flag) return; // 이미 flag가 true면 다음으로 더 안감

        if(cnt == 3) { // 장애물 3개 설치했을 때
            if(checking()) flag = true; // 체크해보고, true면 어쨌튼 감시 피할 수 있는 조합이 있는 것이니 true
            return;
        }

        for (int i = idx; i < space.size(); i++) {
            int[] cur = space.get(i); // 빈 공간에서 하나씩 꺼내기
            int r = cur[0];
            int c = cur[1];

            map[r][c] = 'O'; // 장애물 놓기
            set_obstacle(i + 1, cnt + 1); // 다음 ㄱㄱ
            map[r][c] = 'X'; // 장애물 원복
        }
    }

    /** 선생님 기준으로 현재 방향으로 끝까지 갔을 때 학생 감시할 수 없는지 체크 */
    public static boolean checking() {
        for (int[] cur: teacher) {
            for (int d = 0; d < dr.length; d++) {
                int nr = cur[0];
                int nc = cur[1];

                // 아무리 멀리 있더라도 장애물로 막히기 전까지의 학생들은 모두 볼 수 있다
                while(true) {
                    nr += dr[d];
                    nc += dc[d];

                    // 해당 방향의 가장자리 끝까지 아무도 없는 경우!
                    if(nr<0 || nr>=N || nc<0 || nc>=N) break;

                    if(map[nr][nc] == 'O') break; // 장애물 있는 경우!

                    if(map[nr][nc] == 'S') { // 학생이 있는 경우
                        return false;
                    }
                }
            }
        }
        return true; // 아무도 없었으니까 true 반환
    }
}
