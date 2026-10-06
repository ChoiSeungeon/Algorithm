import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M;
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new List[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());

				graph[p1].add(p2);
				graph[p2].add(p1);
			}

			sb.append("#").append(t).append(" ").append(bfs()).append("\n");
		}

		System.out.println(sb);

	}

	static int bfs() {
		int ans = 0;

		boolean[] visited = new boolean[N + 1];
		Queue<int[]> que = new ArrayDeque<>();

		que.add(new int[] { 1, 0 });
		visited[1] = true;

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int nei : graph[curr[0]]) {
				if (visited[nei] || curr[1] + 1 == 3)
					continue;

				que.add(new int[] { nei, curr[1] + 1 });
				visited[nei] = true;
				ans++;
			}
		}

		return ans;
	}

}
