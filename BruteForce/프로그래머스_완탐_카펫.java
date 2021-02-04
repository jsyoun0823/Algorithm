
class 프로그래머스_완탐_카펫 {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        int sum = brown + yellow;
        
        for(int i=1; i<=yellow; i++) {
            int col = i + 2;
            int row = yellow / i + 2;
            
            if(col < row) continue;
            
            if(col * row == sum) {
                answer[0] = col;
                answer[1] = row;
                break;
            }
        }
        
        return answer;
    }
}