import java.util.*;
import java.io.*;

class Solution {

	static int T, N;
	static double E;
	static int[][] island;

	static class Edge implements Comparable<Edge> {
		int to;
		double pay;

		public Edge(int to, double pay) {
			this.to = to;
			this.pay = pay;
		}

		public int compareTo(Edge e) {
			return Double.compare(this.pay, e.pay);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			island = new int[N][2];

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				island[n][0] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				island[n][1] = Integer.parseInt(st.nextToken());
			}

			E = Double.parseDouble(br.readLine());

			sb.append("#").append(t).append(" ").append(Math.round(prim())).append("\n");
		}

		System.out.println(sb);
	}

	static double prim() {
		Queue<Edge> que = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		for (int i = 1; i < N; i++) {
			double len = Math.pow(Math.abs(island[0][0] - island[i][0]), 2)
					+ Math.pow(Math.abs(island[0][1] - island[i][1]), 2);

			que.add(new Edge(i, len * E));
		}

		visited[0] = true;

		int cnt = 1;
		double ans = 0;

		while (cnt < N) {
			Edge curr = que.poll();

			if (visited[curr.to])
				continue;

			ans += curr.pay;
			visited[curr.to] = true;
			cnt++;

			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;

				double len = Math.pow(Math.abs(island[curr.to][0] - island[i][0]), 2)
						+ Math.pow(Math.abs(island[curr.to][1] - island[i][1]), 2);

				que.add(new Edge(i, len * E));
			}
		}

		return ans;
	}
}