import java.util.*;
import java.io.*;

class Solution {

	static int T, V, E;
	static List<Edge> edges;
	static int[] root;

	static class Edge implements Comparable<Edge> {
		int from, to, w;

		public Edge(int from, int to, int w) {
			this.from = from;
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

			edges = new ArrayList<>();
			root = new int[V];

			for (int i = 1; i < V; i++) {
				root[i] = i;
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				edges.add(new Edge(from, to, w));
			}

			Collections.sort(edges);

			sb.append("#").append(t).append(" ").append(kruskal()).append("\n");

		}

		System.out.println(sb);
	}

	static long kruskal() {
		int cnt = 0;
		long ans = 0;

		for (Edge e : edges) {

			if (find(e.from) == find(e.to))
				continue;

			union(e.from, e.to);

			ans += e.w;
			cnt++;

			if (cnt == V - 2)
				break;
		}

		return ans;
	}

	static int find(int x) {
		if (root[x] == x) {
			return x;
		}

		return root[x] = find(root[x]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return;

		root[rootB] = rootA;
	}
}