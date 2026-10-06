import java.util.*;
import java.io.*;

class Solution {

	static int T, H, W, N;
	static int[] pos, dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					char c = line.charAt(j);

					if (c == '<') {
						pos = new int[] { i, j, 2 };
					} else if (c == '>') {
						pos = new int[] { i, j, 3 };
					} else if (c == '^') {
						pos = new int[] { i, j, 0 };
					} else if (c == 'v') {
						pos = new int[] { i, j, 1 };
					}

					map[i][j] = c;
				}
			}

			N = Integer.parseInt(br.readLine());
			String line = br.readLine();
			for (int n = 0; n < N; n++) {
				char c = line.charAt(n);

				char tank;
				if (c == 'U') {
					pos[2] = 0;
					tank = '^';
				} else if (c == 'D') {
					pos[2] = 1;
					tank = 'v';
				} else if (c == 'L') {
					pos[2] = 2;
					tank = '<';
				} else if (c == 'R') {
					pos[2] = 3;
					tank = '>';
				} else {
					int currX = pos[0], currY = pos[1];
					while (true) {
						int nx = currX + dx[pos[2]];
						int ny = currY + dy[pos[2]];

						if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '#')
							break;

						if (map[nx][ny] == '*') {
							map[nx][ny] = '.';
							break;
						}

						currX = nx;
						currY = ny;
					}
					continue;
				}

				map[pos[0]][pos[1]] = tank;
				
				int nx = pos[0] + dx[pos[2]];
				int ny = pos[1] + dy[pos[2]];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] != '.')
					continue;
				
				map[nx][ny] = tank;
				map[pos[0]][pos[1]] = '.';
				pos[0] = nx;
				pos[1] = ny;
			}

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}

		}

		System.out.println(sb);
	}

}
