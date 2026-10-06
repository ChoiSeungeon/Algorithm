import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			BigInteger a = new BigInteger(st.nextToken());
			BigInteger b = new BigInteger(st.nextToken());

			BigInteger ans = a.add(b);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
