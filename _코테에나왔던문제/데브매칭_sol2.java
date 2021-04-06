public class 데브매칭_sol2 {

    class Solution {
        public int[][] map;

        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];
            map = new int[rows + 1][columns + 1];

            int num = 1;
            for(int i = 1; i <= rows; i++) {
                for(int j = 1; j <= columns; j++) {
                    map[i][j] = num++;
                }
            }

            for(int i = 0; i < queries.length; i++) {
                int[] q = queries[i];
                rotate(q[0], q[1], q[2], q[3]);
                answer[i] = findMin(q[0], q[1], q[2], q[3]);
            }

            return answer;
        }

        public int[] dr = {1, 0, -1, 0};
        public int[] dc = {0, 1, 0, -1};
        public void rotate(int x1, int y1, int x2, int y2) {
            int r = x1;
            int c = y1;

            int dir = 0;
            int temp = map[r][c];

            while(dir < 4) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if(nr >= x1 && nr <= x2 && nc >= y1 && nc <= y2) {
                    map[r][c] = map[nr][nc];
                    r = nr; c = nc;
                } else dir++;
            }

            map[r][c + 1] = temp;
        }

        public int findMin(int x1, int y1, int x2, int y2) {
            int r = x1;
            int c = y1;

            int dir = 0;
            int minNum = map[r][c];
            while(dir < 4) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if(nr >= x1 && nr <= x2 && nc >= y1 && nc <= y2) {
                    minNum = Math.min(map[nr][nc], minNum);
                } else dir++;
            }
            return minNum;
        }
    }
}
