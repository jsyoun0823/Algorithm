import java.util.*;

public class 데브매칭_sol3 {
    public static HashMap<String, Integer> profit;
    public static HashMap<String, String> parents;

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] answer = new int[enroll.length];
        profit = new HashMap<>();
        parents = new HashMap<>();

        for(int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            profit.put(name, 0);
            parents.put(name, referral[i]);
        }

        for(int i = 0; i < seller.length; i++) {
            int money = amount[i] * 100;
            divide(seller[i], money);
        }

        for(int i = 0; i < enroll.length; i++) {
            answer[i] = profit.get(enroll[i]);
        }

        System.out.println(Arrays.toString(answer));
    }

    public static void divide(String cur, int money) {
        if(cur.equals("-")) return;

        if(money * 0.1 < 1) {
            profit.put(cur, profit.getOrDefault(cur, 0) + money);
        } else {
            profit.put(cur, profit.getOrDefault(cur, 0) + (int)(money * 0.9));
            divide(parents.get(cur), (int)(money * 0.1));
        }
    }
}
