public class 카카오메이커스_sol1 {
    class Solution {
        public int solution(int[] gift_cards, int[] wants) {
            int answer = 0;

            int[] giftCount = new int[100001];

            for(int i = 0; i < gift_cards.length; i++) {
                giftCount[gift_cards[i]]++;
            }

            for(int i = 0; i < wants.length; i++) {
                if(giftCount[wants[i]] > 0) {
                    giftCount[wants[i]]--;
                } else {
                    answer++;
                }
            }

            return answer;
        }
    }
}
