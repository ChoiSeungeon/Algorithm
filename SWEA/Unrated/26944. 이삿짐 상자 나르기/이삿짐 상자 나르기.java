import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

	static int T, N, M, ans;
	static int[] weight, power;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			weight = new int[N];
			for (int n = 0; n < N; n++) {
				weight[n] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			power = new int[M];
			for (int m = 0; m < M; m++) {
				power[m] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(weight);
			Arrays.sort(power);

			int boxIdx = N - 1;
			int empIdx = M - 1;

			ans = 0;
			while (boxIdx >= 0 && empIdx >= 0) {
				if (power[empIdx] >= weight[boxIdx]) {
					ans += weight[boxIdx];

					boxIdx--;
					empIdx--;
				} else {
					boxIdx--;
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}
}
