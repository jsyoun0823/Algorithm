import java.util.Scanner;

/** 백트래킹 N-queen 문제 알고리즘 */
public class N_Queen알고리즘 {

	static int N;
	static int[] col; // 각 행의 퀸의 위치를 저장
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N + 1];
		
		setQueens(1); // 1행부터 순차적으로 놓는 코드기 때문에
		System.out.println(answer);
	}
	
	// 퀸을 현재(rowNo) 행에 놓기
	public static void setQueens(int rowNo) {

		if(rowNo > N) { // 퀸을 다 놓아본 상황, 그다음 퀸 없음
			// 4행까지 퀸 놓기 성공한 상황 (유망할때만 보냈기 때문에)
			// 무조건 이 상황은 N퀸을 다 놓은 경우
			// 유망한 경우만 계속해서 따라왔으므로 해가 됨!!
			answer++; // 경우의 수를 올려준다
			return;
		}
		
//		1행 : 1
//		2행 : 1->2 (엎는다)
//		2차원이 아니고 1차원이라서 다시 0으로 되돌리는 작업 X
		
//		1행 : 2
//		2행 : 4
//		3행 : 1
//		4행 : 3 
//		여기서 다음 퀸 번호 5 -> N값 넘어감

		// 가능한 선택지 (1열 ~ N열)
		for (int j = 1; j <= N; j++) {
			col[rowNo] = j; // 현재 행의 j열에 퀸을 두었다
			// 유망한지 체크해서, 가능하면 다음 행으로 가고 불가능이면 안간다
			if(checking(rowNo)) {	// 현재 퀸의 열위치가 가능하다면 다음 퀸으로!
				setQueens(rowNo + 1); 
			}
			// 일차원배열로 퀸의 위치를 관리하므로 시도했던 선택지 열 값을 되돌릴 필요가 없다.
		}
	}

	
//	1,1				1,5
//		2,2  	2,4
//			3,3
//		4,2		4,4
//	5,1				5,5
	
//	방향에 상관 없이, 현재 (3,3)의 행열과 대상 칸의 위치(2,2 등)은 행 1차이 열 1차이 !
	
	/** rowNo행에 퀸을 놓는 게 가능한지 체크하는 메소드.
	 	놓을 수 있으면 true, 놓을 수 없다면 false 반환 */
	public static boolean checking(int rowNo) {
		for (int i = 1; i < rowNo; i++) {
			// 같은 열에 있는지, 대각선에 있는지 체크
			if(col[rowNo] == col[i]  // 직전꺼랑 열 위치가 같으면 (같은 열 체크)
			|| Math.abs(col[rowNo] - col[i]) == rowNo - i)  // 대각선 체크, 행차이와 열차이가 같다.
				return false;
		}
		return true;
	}
}
