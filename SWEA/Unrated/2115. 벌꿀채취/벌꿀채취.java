import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M, C, mHoney, ans;
	static int[][] map;

	static class Area {
		int i, j, income;

		public Area(int i, int j, int income) {
			this.i = i;
			this.j = j;
			this.income = income;
		}

		@Override
		public String toString() {
			return income + "";
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
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			LinkedList<Integer> window;
			List<Area> areaList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				window = new LinkedList<>();
				for (int j = 0; j < N; j++) {

					window.add(map[i][j]);

					// 크기가 M인 윈도우로 고를 수 있는 모든 벌통의 범위를 탐색
					if (j >= M - 1) {
						mHoney = 0;
						
						// 선택된 벌통의 범위에서 나올 수 있는 최대값 선택
						selectHoney(window, 0, 0, 0);
						
						// 각 범위에 대한 최대 값 저장
						areaList.add(new Area(i, j, mHoney));
						window.poll();
					}

				}
			}

			ans = 0;
			
			// 각 범위에 대한 최댓값 중에 값을 최대로 할 수 있는 2개의 범위를 선택  
			selectArea(areaList, 0, 0, 0, -1, -1);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	static void selectHoney(LinkedList<Integer> window, int honey, int c, int idx) {
		if (idx == M)
			return;

		int num = window.get(idx);

		if (c + num <= C) {
			mHoney = Math.max(mHoney, honey + num * num);
			selectHoney(window, honey + num * num, c + num, idx + 1);
		}

		selectHoney(window, honey, c, idx + 1);
	}

	static void selectArea(List<Area> areaList, int num, int sum, int idx, int preI, int preJ) {
		if (num == 2) {
			ans = Math.max(ans, sum);
			return;
		}

		if (idx == areaList.size())
			return;

		if (preI != areaList.get(idx).i || preJ <= areaList.get(idx).j - M)
			selectArea(areaList, num + 1, sum + areaList.get(idx).income, idx + 1, areaList.get(idx).i,
					areaList.get(idx).j);

		selectArea(areaList, num, sum, idx + 1, preI, preJ);
	}

}
