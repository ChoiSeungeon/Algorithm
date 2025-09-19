import java.util.Scanner;

public class Main {

	static int N;
	static int[] stairs;
	static int[] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		stairs = new int[N + 1];
		memo = new int[N + 1];

		for (int i = 1; i <= N; i++)
			stairs[i] = sc.nextInt();

		memo[1] = stairs[1];
		if (N == 1) {
			System.out.println(memo[N]);
			return;
		}
		memo[2] = memo[1] + stairs[2];
		if (N == 2) {
			System.out.println(memo[N]);
			return;
		}
		memo[3] = Math.max(memo[1] + stairs[3], stairs[2] + stairs[3]);
		if (N == 3) {
			System.out.println(memo[N]);
			return;
		}

		for (int i = 4; i <= N; i++) {
			int a = memo[i - 2] + stairs[i];
			int b = memo[i - 3] + stairs[i - 1] + stairs[i];

			memo[i] = Math.max(a, b);
		}

		System.out.println(memo[N]);

	}

}
