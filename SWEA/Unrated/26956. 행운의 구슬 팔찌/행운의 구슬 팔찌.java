import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M, K;
	static LinkedList<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			list = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			int ref = list.peekFirst();
			int idx = 0;
			for (int k = 0; k < K; k++) {
				idx = (idx + M) % N;
				if (idx == 0) {
					list.add(N, list.get(N - 1) + ref);
					idx = N;
				} else {
					list.add(idx, list.get(idx) + list.get(idx - 1));
				}
				N++;
			}

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < 10; i++) {
				if (list.isEmpty()) {
					break;
				}

				sb.append(list.pollLast()).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
