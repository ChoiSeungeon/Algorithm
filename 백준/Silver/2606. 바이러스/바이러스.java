import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static LinkedList<Integer> queue;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		queue = new LinkedList<>();

		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			graph[s].add(e);
			graph[e].add(s);
		}

		bfs();

		System.out.println(sum);

	}

	static void bfs() {
		queue.add(1);
		visited[1] = true;

		while (!queue.isEmpty()) {
			int next = queue.poll();

			for (int nei : graph[next]) {
				if (!visited[nei]) {
					queue.add(nei);
					visited[nei] = true;
					sum++;
				}
			}
		}
	}

}
