public class 프로그래머스_문자열압축 {
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
    }
    public static int solution(String s) {

        // 2a2ba3c
        // 2ababcdcd
        // abc abc de de
        int min = Integer.MAX_VALUE;
        // 1부터 s.size / 2 까지의 단위로 잘라보기
        for(int n = 1; n < s.length() / 2; n++) {
            min = Math.min(min, devide(n, s));
        }
        return min;
    }

    public static int devide(int n, String s) {
        String prev = s.substring(0, n);
        int cnt = 1;
        String str = "";
        // aab bac cc
        for(int i = n; i < s.length(); i += n) {
            int last = Math.min(i + n, s.length());
            String cur = s.substring(i, last);
            if(cur.equals(prev)) {
                cnt++;
            } else {
                if(cnt > 1) {
                    str += (cnt) + prev;
                } else {
                    str += prev;
                }
                cnt = 1;
                prev = cur;
            }

        }

        if(cnt > 1) {
            str += (cnt) + prev;
        } else {
            str += prev;
        }
        return str.length();
    }

}
