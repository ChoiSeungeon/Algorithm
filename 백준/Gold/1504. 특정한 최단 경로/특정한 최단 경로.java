import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, E;
	static int[][] graph;
	static final int INF = 1_000_000_000;

	static class State implements Comparable<State> {
		int to, dist;

		public State(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(State o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (graph[u][v] > w)
				graph[u][v] = w;
			if (graph[v][u] > w)
				graph[v][u] = w;
		}

		st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());

		int case1 = dijkstra(1, p1);
		int case2 = dijkstra(1, p2);
		int case3 = dijkstra(p1, p2);
		int case4 = dijkstra(p2, p1);
		int case5 = dijkstra(p1, N);
		int case6 = dijkstra(p2, N);
		
		if (case1 == -1 || case2 == -1 ||case3 == -1 ||case4 == -1 ||case5 == -1 ||case6 == -1)
			System.out.println(-1);
		else
			System.out.println(Math.min(case1 + case3 + case6, case2 + case4 + case5));

		br.close();
	}

	static int dijkstra(int s, int e) {
		PriorityQueue<State> que = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[s] = 0;
		que.add(new State(s, 0));

		while (!que.isEmpty()) {
			State curr = que.poll();
			if (visited[curr.to] || curr.dist != dist[curr.to])
				continue;

			if (curr.to == e) {
				return dist[e];
			}

			visited[curr.to] = true;

			for (int i = 1; i <= N; i++) {
				if (visited[i] || graph[curr.to][i] == INF)
					continue;

				if (dist[i] > curr.dist + graph[curr.to][i]) {
					dist[i] = curr.dist + graph[curr.to][i];
					que.add(new State(i, dist[i]));
				}
			}
		}

		return -1;
	}

}
