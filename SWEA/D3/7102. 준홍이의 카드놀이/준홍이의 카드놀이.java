import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int min = Math.min(N, M);
			int cnt = Math.abs(N - M) + 1;
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < cnt; i++) {
				sb.append(++min).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
