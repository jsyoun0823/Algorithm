public class 코오롱_구간합 {
    public static void main(String[] args) {
        int N = 10;
        int S = 15;
        int[] A = {5, 1, 3, 5, 10, 7, 4, 9, 2, 8};

        int min = 1000000;
        int left = 0;
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += A[i];

            while(sum >= S) {
                if(min > i - left) min = i - left + 1;

                sum -= A[left];
                left++;
            }
        }

        System.out.println(min);
    }
}
