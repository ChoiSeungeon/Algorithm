import java.util.Scanner;

public class Main {

	static int N, V, ans = 0;
	static boolean[] w, h, leftUpRightDown, leftDownRightUp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		w = new boolean[N];
		h = new boolean[N];
		V = (N - 1) * 2 + 1;
		leftUpRightDown = new boolean[V];
		leftDownRightUp = new boolean[V];

		for (int i = 0; i < N; i++) {
			dfs(0, i, 1);
		}

		System.out.println(ans);

	}

	static void dfs(int x, int y, int cnt) {
		if (w[x] || h[y] || leftUpRightDown[x - y + N - 1] || leftDownRightUp[x + y])
			return;

		w[x] = true;
		h[y] = true;
		leftUpRightDown[x - y + N - 1] = true;
		leftDownRightUp[x + y] = true;

		if (cnt == N)
			ans++;

		for (int nei = 0; nei < N; nei++) {
			if (x + 1 < N) {
				dfs(x + 1, nei, cnt + 1);
			}
		}

		w[x] = false;
		h[y] = false;
		leftUpRightDown[x - y + N - 1] = false;
		leftDownRightUp[x + y] = false;
	}

}
