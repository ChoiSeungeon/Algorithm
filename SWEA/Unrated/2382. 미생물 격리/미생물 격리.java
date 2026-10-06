import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M, K;
	static Micro[][] map;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	static class Micro {
		int dir, size, maxSize;

		public Micro(int size, int maxSize, int dir) {
			this.size = size;
			this.maxSize = maxSize;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new Micro[N][N];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;

				map[x][y] = new Micro(size, size, dir);
			}

			for (int m = 0; m < M; m++) {
				map = move();
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == null)
						continue;

					ans += map[i][j].size;
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static Micro[][] move() {
		Micro[][] tmpMap = new Micro[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == null)
					continue;

				Micro curMicro = map[i][j];
				curMicro.maxSize = curMicro.size;

				int nx = i + dx[curMicro.dir];
				int ny = j + dy[curMicro.dir];

				if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
					curMicro.size /= 2;
					if(curMicro.size == 0) {
						continue;
					}
					
					curMicro.dir += 1;
					if (curMicro.dir == 2) {
						curMicro.dir = 0;
					} else if (curMicro.dir == 4) {
						curMicro.dir = 2;
					}
				}

				if (tmpMap[nx][ny] != null) {
					Micro otherMicro = tmpMap[nx][ny];
					int size = curMicro.size + otherMicro.size;
					int maxSize, dir;

					if (curMicro.maxSize > otherMicro.maxSize) {
						dir = curMicro.dir;
						maxSize = curMicro.maxSize;
					} else {
						dir = otherMicro.dir;
						maxSize = otherMicro.maxSize;
					}

					tmpMap[nx][ny] = new Micro(size, maxSize, dir);
				} else {
					tmpMap[nx][ny] = curMicro;
				}
			}
		}

		return tmpMap;
	}

}
