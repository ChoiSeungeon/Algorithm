/*
 * 이전에 풀었던 D_Question2.java의 snail에서 해결 방법 착안
 * 정삼각형을 n*n 배열에 맞추기 위해 직삼각형으로 변환하여 해결
 * 
 * 메모리: 141 MB
 * 실행 시간: 15.64 ms
 */

class Solution {
	public int[] solution(int n) {

		// 하, 우, 대각선
		int[] dx = { 1, 0, -1 };
		int[] dy = { 0, 1, -1 };

		int[][] map = new int[n][n];

		int x = 0, y = 0, num = 1, dir = 0;
		while (true) {
			map[x][y] = num++;

			if (n == 1)
				break;

			int nx = x + dx[dir % 3];
			int ny = y + dy[dir % 3];

			// 막다른 길인 경우 방향 전환
			if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] != 0) {
				dir++;
				nx = x + dx[dir % 3];
				ny = y + dy[dir % 3];

				// 두번 연속 막다른 길인 경우 종료로 판단
				if (map[nx][ny] != 0)
					break;
			}

			x = nx;
			y = ny;
		}

		int[] answer = new int[num - 1];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 역삼각형으로 변환했으므로 0인 곳은 스킵
				if (map[i][j] == 0)
					continue;
				answer[cnt++] = map[i][j];
			}
		}

		return answer;
	}
}