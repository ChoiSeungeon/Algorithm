import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static LinkedList<Integer> queue;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];

		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			graph[s].add(e);
			graph[e].add(s);
		}
		
		for (int i = 1; i < n + 1; i++) {
			Collections.sort(graph[i]);
		}

		visited = new boolean[n + 1];
		dfs(v);
		
		System.out.println();
		
		visited = new boolean[n + 1];
		queue = new LinkedList<>();
		bfs(v);

	}

	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");

		for (int nei : graph[v]) {
			if (!visited[nei])
				dfs(nei);
		}
	}

	static void bfs(int v) {
		int curr = v;
		queue.add(curr);
		visited[curr] = true;
		
		while(queue.size() != 0) {
			curr = queue.poll();
			System.out.print(curr + " ");
			
			for (int nei : graph[curr]) {
				if (!visited[nei]) {
					queue.add(nei);
					visited[nei] = true;
				}
			}
		}
	}

}
