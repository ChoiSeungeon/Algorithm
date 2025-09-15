import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static ArrayList<Node>[] graph;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;

	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		int s = Integer.parseInt(br.readLine());
		dist = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			dist[i] = INF;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[u].add(new Node(v, w));
		}

		dijkstra(s);

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF");
			} else {
				sb.append(dist[i]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

		br.close();
	}

	static void dijkstra(int s) {
		boolean[] visited = new boolean[V + 1];
		PriorityQueue<Node> que = new PriorityQueue<>();

		dist[s] = 0;
		que.add(new Node(s, 0));

		while (!que.isEmpty()) {
			Node curr = que.poll();
			if (visited[curr.v])
				continue;
			visited[curr.v] = true;

			for (Node node : graph[curr.v]) {
				if (visited[node.v])
					continue;

				if (dist[curr.v] + node.w < dist[node.v]) {
					dist[node.v] = dist[curr.v] + node.w;
					que.add(new Node(node.v, dist[node.v]));
				}

			}
		}
	}

}
