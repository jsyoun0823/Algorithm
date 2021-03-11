import java.util.*;

public class 프로그래머스_프렌즈4블록 {

    public static int cnt;
    public static char[][] map;
    public static Set<Block> set = new HashSet<>();

    public static class Block {
        int r, c;
        public Block(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            return r == block.r &&
                    c == block.c;
        }
        // 0 1 2 3
        // 0 1 3 2
        // b d d a b, d, d, a
        // s = bdda

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        map = new char[m][n];
        for(int i=0; i<m; i++) {
            map[i] = board[i].toCharArray();
        }

        cnt = 0;

        while(true) {
            set.clear();

            check_block(m, n); // 2x2 모양의 블록 있는지 체크

            if(set.isEmpty()) break;

            remove_block(); // 블록 지우기

            down_block(m, n); // 블록 지운 후, 위->아래로 블록 떨어짐
        }

        System.out.println(cnt);
    }

    public static void check_block(int m, int n) {
        for(int i = 0; i < m-1; i++) {
            for(int j = 0; j < n-1; j++) {
                char cur = map[i][j];
                if(cur != '.'
                        && cur == map[i][j+1]
                        && cur == map[i+1][j]
                        && cur == map[i+1][j+1]) {
                    set.add(new Block(i, j));
                    set.add(new Block(i, j+1));
                    set.add(new Block(i+1, j));
                    set.add(new Block(i+1, j+1));
                }
            }
        }
    }

    public static void remove_block() {
        for(Block b : set) {
            System.out.println(b.r +  " " + b.c);
            map[b.r][b.c] = '.';
            cnt++;
        }
    }

    public static void down_block(int m, int n) {
        for (int c = 0; c < n; c++) { // 열 고정
            int r = m - 1; // 제일 아래행 부터 시작
            while(r > 0) {
                if(map[r][c] == '.') {
                    int nr = r - 1; // 직전 행
                    while(nr > 0 && map[nr][c] == '.') --nr; // 처음 만나는 벽돌 찾기
                    map[r][c] = map[nr][c];
                    map[nr][c] = '.';
                }
                --r;
            }
        }
    }
}
