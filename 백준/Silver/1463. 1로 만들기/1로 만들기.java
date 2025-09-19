import java.util.Scanner;

public class Main {

	static int N;
	static int[] memo = new int[1000001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		memo[1] = 0;

		N = sc.nextInt();
		
		make1();
		
		System.out.println(memo[N]);

	}

	static void make1() {
		for(int i = 2; i <= N; i++) {
			memo[i] = memo[i-1] + 1;
			if (i % 3 == 0)
				memo[i] = Math.min(memo[i], memo[i/3] + 1);
			if (i % 2 == 0)
				memo[i] = Math.min(memo[i], memo[i/2] + 1);
		}
	}

}
