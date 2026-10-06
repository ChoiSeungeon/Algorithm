import java.util.*;
import java.io.*;

class Solution {

	static int T, K;
	static LinkedList<Integer>[] magnet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());

			magnet = new LinkedList[4];

			// 4개의 자석 정보 입력
			for (int i = 0; i < 4; i++) {
				magnet[i] = new LinkedList<>();
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < 8; j++) {
					magnet[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			// K번의 회전 명령 수행
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());

				int n = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());

				// 각 자석의 회전 방향 저장
				int[] doRotate = new int[4];
				doRotate[n] = dir;

				// 오른쪽 자석으로 회전 전파
				for (int r = n; r < 3; r++) {
					if (magnet[r].get(2) != magnet[r + 1].get(6)) {
						doRotate[r + 1] = doRotate[r] * -1;
						continue;
					}

					break;
				}

				// 왼쪽 자석으로 회전 전파
				for (int l = n; l > 0; l--) {
					if (magnet[l].get(6) != magnet[l - 1].get(2)) {
						doRotate[l - 1] = doRotate[l] * -1;
						continue;
					}

					break;
				}

				// 전파 여부를 모두 확인한 후 실제 회전
				for (int i = 0; i < 4; i++) {
					if (doRotate[i] != 0) {
						rotate(i, doRotate[i]);
					}
				}
			}

			// 각 자석의 12시 방향 극을 확인해 점수 계산
			int ans = 0;
			for (int i = 0; i < 4; i++) {
				if (magnet[i].peekFirst() == 1) {
					ans += Math.pow(2, i);
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	// dir = 1: 시계 방향, dir = -1: 반시계 방향
	static void rotate(int m, int dir) {
		if (dir == 1) {
			magnet[m].addFirst(magnet[m].pollLast());
		} else {
			magnet[m].addLast(magnet[m].pollFirst());
		}
	}
}