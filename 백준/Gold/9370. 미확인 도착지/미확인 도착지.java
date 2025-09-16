import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M, t, s, g, h;
	static int[] candidate;
	static ArrayList<Edge>[] map;
	static final int INF = 1_000_000_000;

	static class Edge {
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

	static class State implements Comparable<State> {
		int v, dist;

		public State(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(State o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new ArrayList[N + 1];
			for (int j = 1; j <= N; j++)
				map[j] = new ArrayList<>();
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				map[u].add(new Edge(v, w));
				map[v].add(new Edge(u, w));
			}

			candidate = new int[t];
			for (int j = 0; j < t; j++) {
				candidate[j] = Integer.parseInt(br.readLine());
			}

			int[] dist1 = dijkstra(s);
			int[] dist2 = dijkstra(g);
			int[] dist3 = dijkstra(h);

			Arrays.sort(candidate);
			for (int e : candidate) {
				if ((dist1[e] == dist1[g] + dist2[h] + dist3[e]) || (dist1[e] == dist1[h] + dist3[g] + dist2[e])) {
					sb.append(e).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

		br.close();
	}

	static int[] dijkstra(int start) {
		PriorityQueue<State> que = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		que.add(new State(start, 0));

		while (!que.isEmpty()) {
			State curr = que.poll();
			if (curr.dist > dist[curr.v])
				continue;

			for (Edge e : map[curr.v]) {
				if (dist[e.to] > curr.dist + e.w) {
					dist[e.to] = curr.dist + e.w;
					que.add(new State(e.to, dist[e.to]));
				}
			}
		}

		return dist;
	}

}
