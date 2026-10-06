import java.util.*;
import java.io.*;

class Solution {

	static int T, ans;
	static int[] price, useCase;;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			price = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			useCase = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				useCase[i] = Integer.parseInt(st.nextToken());
			}

			int[] dp = new int[13];
			dp[1] = Math.min(useCase[1] * price[0], price[1]);
			for (int i = 2; i <= 12; i++) {
				dp[i] = Math.min(dp[i - 1] + useCase[i] * price[0], dp[i - 1] + price[1]);

				if (i >= 3) {
					dp[i] = Math.min(dp[i], dp[i - 3] + price[2]);
				}
			}

			dp[12] = Math.min(dp[12], price[3]);

			sb.append("#").append(t).append(" ").append(dp[12]).append("\n");
		}

		System.out.println(sb);
	}
}
