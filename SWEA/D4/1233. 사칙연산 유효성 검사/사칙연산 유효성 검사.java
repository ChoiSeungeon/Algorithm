import java.util.*;
import java.io.*;

class Solution {

	static int T = 10, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int ans = 1;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());

				String num = st.nextToken();
				String node = st.nextToken();
				
				if ((i <= N / 2) && !(node.equals("*") || node.equals("/") || node.equals("-") || node.equals("+"))) {
					ans = 0;
				}
				

				if ((i > N / 2) && (node.equals("*") || node.equals("/") || node.equals("-") || node.equals("+"))) {
					ans = 0;
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

}
