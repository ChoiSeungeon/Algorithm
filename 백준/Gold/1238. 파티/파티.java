import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, X;
	static List<Edge>[] map;
	static final int INF = 1_000_000_000;

	static class Edge {
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

	static class State implements Comparable<State> {
		int v, d;

		public State(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(State o) {
			return this.d - o.d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			map[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			map[u].add(new Edge(v, w));
		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			int distGo = dijkstra(i, X);
			int distBack = dijkstra(X, i);
			max = Math.max(max, distGo + distBack);
		}

		System.out.println(max);

		br.close();
	}

	static int dijkstra(int s, int x) {
		PriorityQueue<State> que = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[s] = 0;
		que.add(new State(s, 0));

		while (!que.isEmpty()) {
			State curr = que.poll();
			if (curr.d != dist[curr.v])
				continue;
			
			if (curr.v == x)
				return dist[x];

			for (Edge e : map[curr.v]) {
				if (dist[e.to] > curr.d + e.w) {
					dist[e.to] = curr.d + e.w;
					que.add(new State(e.to, dist[e.to]));
				}
			}
		}

		return -1;
	}

}
