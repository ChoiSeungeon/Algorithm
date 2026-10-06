import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

	static int T;
	static double N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Double.parseDouble(br.readLine());

			int ans = 0;
			while (N != 2) {
				
				long k = (long) Math.sqrt(N);
				

				if (k * k == N) {
					ans++;
					N = k;
				} else {
					ans += 1 + (Math.pow((k + 1), 2) - N);
					N = k + 1;
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

}
