import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] graph;
	static StringBuilder sb;
	static final int INF = 1_000_000_000;

	static class State implements Comparable<State> {
		int to, dist, cnt;
		StringBuilder path;

		public State(int to, int dist, int cnt) {
			this.to = to;
			this.dist = dist;
			this.cnt = cnt;
			path = new StringBuilder();
		}

		@Override
		public int compareTo(State o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++)
			Arrays.fill(graph[i], INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (graph[u][v] > w)
				graph[u][v] = w;
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		dijkstra(s, e);

		System.out.println(sb);

		br.close();
	}

	static void dijkstra(int s, int e) {
		PriorityQueue<State> que = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[s] = 0;
		State start = new State(s, 0, 1);
		start.path.append(s).append(" ");
		que.add(start);

		while (!que.isEmpty()) {
			State curr = que.poll();

			if (visited[curr.to])
				continue;
			visited[curr.to] = true;

			if (curr.to == e) {
				sb.append(dist[e]).append("\n");
				sb.append(curr.cnt).append("\n");
				sb.append(curr.path);
				return;
			}

			for (int i = 1; i <= N; i++) {
				if (visited[i] || graph[curr.to][i] == INF)
					continue;

				if (dist[i] > curr.dist + graph[curr.to][i]) {
					dist[i] = curr.dist + graph[curr.to][i];
					State next = new State(i, dist[i], curr.cnt + 1);
					next.path.append(curr.path).append(i).append(" ");
					que.add(next);
				}
			}

		}

	}

}
