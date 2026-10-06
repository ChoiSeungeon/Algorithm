import java.util.*;
import java.io.*;

class Solution {

	static int T, N, ans;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static WarmHole[] warmHole;

	static class WarmHole {
		int x1, y1;
		int x2, y2;

		public WarmHole(int x1, int y1) {
			this.x1 = x1;
			this.y1 = y1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			warmHole = new WarmHole[11];
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());

				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());

					if (num >= 6) {
						if (warmHole[num] == null) {
							warmHole[num] = new WarmHole(i, j);
						} else {
							warmHole[num].x2 = i;
							warmHole[num].y2 = j;
						}
					}

					map[i][j] = num;
				}
			}

			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int d = 0; d < 4; d++) {
						if (map[i][j] != 0)
							continue;
						ans = Math.max(ans, shoot(i, j, d));
					}
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static int shoot(int sx, int sy, int dir) {
		int score = 0;

		int cx = sx;
		int cy = sy;

		while (true) {
			int nx = cx + dx[dir];
			int ny = cy + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				score++;
				dir = mirror(dir);

				cx = nx;
				cy = ny;

				continue;
			}

			int num = map[nx][ny];
			if (num == -1 || (nx == sx && ny == sy)) {
				return score;
			} else if (num >= 6) {
				if (warmHole[num].x1 == nx && warmHole[num].y1 == ny) {
					nx = warmHole[num].x2;
					ny = warmHole[num].y2;
				} else {
					nx = warmHole[num].x1;
					ny = warmHole[num].y1;
				}
			} else if (num <= 5 && num > 0) {
				score++;

				if (num == 1) {
					if (dir == 0 || dir == 3) {
						dir = mirror(dir);
					} else {
						dir = (dir + 2) % 4;
					}
				} else if (num == 2) {
					if (dir == 3 || dir == 1) {
						dir = mirror(dir);
					} else {
						dir = (dir + 3) % 4;
					}
				} else if (num == 3) {
					if (dir == 1 || dir == 2) {
						dir = mirror(dir);
					} else {
						dir = (dir + 2) % 4;
					}
				} else if (num == 4) {
					if (dir == 0 || dir == 2) {
						dir = mirror(dir);
					} else {
						dir = (dir + 1) % 4;
					}
				} else if (num == 5) {
					dir = mirror(dir);
				}
			}

			cx = nx;
			cy = ny;
		}
	}

	static int mirror(int dir) {
		if (dir == 0 || dir == 1) {
			return (dir + 1) % 2;
		} else {
			return (dir - 1) % 2 + 2;
		}
	}
}
