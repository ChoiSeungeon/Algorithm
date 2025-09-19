import java.util.Scanner;

public class Main {

	static int N, K;
	static int[] coins;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ans = 0;

		N = sc.nextInt();
		K = sc.nextInt();

		coins = new int[N];
		for (int i = 0; i < N; i++) {
			coins[i] = sc.nextInt();
		}

		for (int i = N - 1; i >= 0; i--) {
			ans += K / coins[i];
			K = K % coins[i];
		}

		System.out.println(ans);

	}

}
