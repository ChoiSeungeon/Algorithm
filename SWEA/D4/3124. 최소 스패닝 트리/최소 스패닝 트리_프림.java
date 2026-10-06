import java.util.*;
import java.io.*;

class Solution {

	static int T, V, E;
	static List<Edge>[] graph;

	static class Edge implements Comparable<Edge> {
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}

		public int compareTo(Edge o) {
			return Integer.compare(w, o.w);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken()) + 1;
			E = Integer.parseInt(st.nextToken());

			graph = new ArrayList[V];
			for (int i = 1; i < V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				graph[from].add(new Edge(to, w));
				graph[to].add(new Edge(from, w));
			}

			sb.append("#").append(t).append(" ").append(prim()).append("\n");

		}

		System.out.println(sb);
	}

	static long prim() {
		Queue<Edge> que = new PriorityQueue<>();
		boolean[] visited = new boolean[V];

		for (Edge e : graph[1]) {
			que.add(e);
		}
		visited[1] = true;

		int cnt = 1;
		long ans = 0;
		while (cnt < V - 1) {
			Edge curr = que.poll();

			if (visited[curr.to])
				continue;

			ans += curr.w;
			visited[curr.to] = true;
			cnt++;

			for (Edge e : graph[curr.to]) {
				que.add(e);
			}
		}

		return ans;
	}
}
