import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] memo;
	static ArrayList<Integer> coins;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		coins = new ArrayList<>();
		memo = new int[K + 1];
		Arrays.fill(memo, INF);
		for (int i = 0; i < N; i++) {
			int coin = Integer.parseInt(br.readLine());
			if (coin <= K && memo[coin] == INF) {
				coins.add(coin);
				memo[coin] = 1;
			}
		}

		for (int i = coins.get(0) + 1; i <= K; i++) {
			for (Integer c : coins) {
				if (i - c < 1)
					continue;
				memo[i] = Math.min(memo[i], memo[i - c] + 1);
			}
		}

		System.out.println(memo[K] == INF ? -1 : memo[K]);

		br.close();
	}

}
