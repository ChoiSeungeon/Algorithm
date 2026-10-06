import java.util.*;
import java.io.*;

class Solution {

	static int T, N, peopleNum, ans;
	static int[] stair1Dis, stair2Dis;
	static List<Integer> stair1Choice, stair2Choice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		int[][] people = new int[10][2];
		int[][] stair = new int[2][3];
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			peopleNum = 0;
			int stairNum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					String token = st.nextToken();
					if (token.equals("1")) {
						people[peopleNum][0] = i;
						people[peopleNum++][1] = j;
					} else if (!token.equals("0")) {
						stair[stairNum][0] = i;
						stair[stairNum][1] = j;
						stair[stairNum++][2] = Integer.parseInt(token);
					}
				}
			}

			stair1Dis = new int[peopleNum];
			stair2Dis = new int[peopleNum];

			for (int i = 0; i < peopleNum; i++) {
				stair1Dis[i] = Math.abs(people[i][0] - stair[0][0]) + Math.abs(people[i][1] - stair[0][1]);
				stair2Dis[i] = Math.abs(people[i][0] - stair[1][0]) + Math.abs(people[i][1] - stair[1][1]);
			}

			ans = Integer.MAX_VALUE;
			stair1Choice = new ArrayList<>();
			stair2Choice = new ArrayList<>();
			choice(0, stair[0][2], stair[1][2]);
			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static void choice(int cnt, int s1, int s2) {
		if (cnt == peopleNum) {
			Collections.sort(stair1Choice);
			Collections.sort(stair2Choice);

			List<Integer> inStair1 = new ArrayList<>();
			for (int dis : stair1Choice) {
				int time = dis + 1;

				while (!inStair1.isEmpty() && inStair1.get(0) <= time) {
					inStair1.remove(0);
				}

				if (inStair1.size() == 3) {
					time = inStair1.get(0);
					inStair1.remove(0);
				}

				time += s1;
				inStair1.add(time);
			}

			List<Integer> inStair2 = new ArrayList<>();
			for (int dis : stair2Choice) {
				int time = dis + 1;

				while (!inStair2.isEmpty() && inStair2.get(0) <= time) {
					inStair2.remove(0);
				}

				if (inStair2.size() == 3) {
					time = inStair2.get(0);
					inStair2.remove(0);
				}

				time += s2;
				inStair2.add(time);
			}

			int s1Time = 0;
			if (inStair1.size() != 0) {
				s1Time = inStair1.get(inStair1.size() - 1);
			}

			int s2Time = 0;
			if (inStair2.size() != 0) {
				s2Time = inStair2.get(inStair2.size() - 1);
			}

			ans = Math.min(ans, Math.max(s1Time, s2Time));
			return;
		}

		stair1Choice.add(stair1Dis[cnt]);
		choice(cnt + 1, s1, s2);
		stair1Choice.remove(stair1Choice.indexOf(stair1Dis[cnt]));

		stair2Choice.add(stair2Dis[cnt]);
		choice(cnt + 1, s1, s2);
		stair2Choice.remove(stair2Choice.indexOf(stair2Dis[cnt]));
	}

}
