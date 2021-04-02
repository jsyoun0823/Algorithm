public class 프로그래머스_스킬트리 {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        int answer = 0;
        for(String str : skill_trees) {
            // B,A,C,D,E
            // C B D
            int idx = 0;
            boolean flag = true;

            for(int j = 0; j < str.length(); j++) {
                int find = skill.indexOf(str.charAt(j));
                if(find == -1) continue;
                if(idx != find) {
                    flag = false;
                    break;
                } else idx++;
            }
            if(flag) answer++;
        }

        System.out.println(answer);
    }
}
