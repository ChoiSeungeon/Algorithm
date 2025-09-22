import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static int[] parent;

	static class Edge implements Comparable<Edge> {

		int from, to, w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Edge> que = new PriorityQueue<>();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			que.add(new Edge(from, to, w));
		}

		parent = new int[V + 1];
		for (int i = 1; i <= V; i++)
			parent[i] = i;

		int num = 0, ans = 0;
		while (!que.isEmpty()) {
			if (num == V - 1)
				break;

			Edge edge = que.poll();
			if (union(edge.from, edge.to)) {
				num++;
				ans += edge.w;
			}
		}

		System.out.println(ans);

		br.close();
	}

	static boolean union(int v1, int v2) {
		int v1p = find(v1);
		int v2p = find(v2);
		if (v1p == v2p)
			return false;

		parent[v2p] = v1p;
		return true;
	}

	static int find(int start) {
		if (parent[start] == start)
			return start;
		return parent[start] = find(parent[start]);
	}

}
