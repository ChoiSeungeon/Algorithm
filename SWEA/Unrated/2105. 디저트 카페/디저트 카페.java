import java.util.*;
import java.io.*;

class Solution {

	static int T, N, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					set = new HashSet<>();
					set.add(map[i][j]);
					visited[i][j] = true;
					dfs(i, j, 0, i, j);
					visited[i][j] = false;
				}
			}

			if(ans < 4)
				ans = -1;
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int x, int y, int dir, int sx, int sy) {
		for (int d = dir; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (nx == sx && ny == sy) {
				ans = Math.max(ans, set.size());
				return;
			}
			
			if(visited[nx][ny])
				continue;
			
			if (!set.add(map[nx][ny])) {
				continue;
			}

			visited[nx][ny] = true;

			dfs(nx, ny, d, sx, sy);
			
			set.remove(map[nx][ny]);

			visited[nx][ny] = false;
		}
	}

}
