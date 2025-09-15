import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];
		dist = new int[n][m];

		for (int i = 0; i < n; i++) {
			char[] nums = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = nums[j] - '0';
			}
		}

		bfs(n, m);

	}

	static void bfs(int n, int m) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		LinkedList<Integer[]> queue = new LinkedList<>();

		int currDist = 1;
		visited[0][0] = true;
		queue.add(new Integer[] { 0, 0 });
		dist[0][0] = currDist;

		while (queue.size() != 0) {
			Integer[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];

			currDist = dist[x][y] + 1;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (visited[nx][ny] || map[nx][ny] == 0)
					continue;

				queue.add(new Integer[] { nx, ny });
				visited[nx][ny] = true;
				dist[nx][ny] = currDist;

			}

		}

		System.out.println(dist[n - 1][m - 1]);

	}

}
