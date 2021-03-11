import java.util.*;

public class 코오롱_사전순다음문자열 {
    static int N;
    static int[] nums;
    static char[] chars;
    static boolean[] selected;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        String s = "ZXBBBA";
        String answer = "";

        chars = s.toCharArray(); // char 배열로 변환해서 저장
        N = s.length();
        nums = new int[N];
        selected = new boolean[N];

        perm(0);

        List<String> list = new ArrayList<>(set); // 정렬을 위해 list로 변환
        Collections.sort(list); // 사전 순으로 정렬

        // 갯수가 1개면 (ex. AAA), 혹은 사전의 마지막에 위치한다면
        if(list.size() <= 1 || list.get(list.size() - 1).equals(s)) {
            answer = s; // 문자열 그대로 반환
            System.out.println(answer);
            return;
        }

        // 아니라면, 정렬된 리스트에서 주어진 문자열 찾아서 다음 위치에 있는 문자열 반환
        for (int i = 0; i < list.size() - 1; i++) {
            String cur = list.get(i);
            if(cur.equals(s)) {
                answer = list.get(i + 1);
                break;
            }
        }

        System.out.println(answer);
    }

    /** 주어진 문자열의 문자들로 만들 수 있는 문자열들 set에 저장하는 순열 함수 */
    public static void perm(int cnt) {
        if(cnt == N) {
            String temp = "";
            for(int i : nums) {
                temp += chars[i];
            }
            set.add(temp); // 중복 방지를 위해 set에 저장
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!selected[i]) {
                selected[i] = true;
                nums[cnt] = i;
                perm(cnt + 1);
                selected[i] = false;
            }
        }
    }
}
