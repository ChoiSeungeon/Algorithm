import java.util.*;

class Solution {
	public int solution(String[] friends, String[] gifts) {

		StringTokenizer st;

		int n = friends.length;
		int[][] giftDia = new int[n][n];

		for (int i = 0; i < gifts.length; i++) {
			st = new StringTokenizer(gifts[i]);

			String from = st.nextToken();
			String to = st.nextToken();

			int fromIdx = -1;
			int toIdx = -1;
			for (int j = 0; j < n; j++) {
				if (from.equals(friends[j]))
					fromIdx = j;
				if (to.equals(friends[j]))
					toIdx = j;
			}

			giftDia[fromIdx][toIdx] += 1;
		}

		int[] giftScore = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;

				giftScore[i] += giftDia[i][j];
				giftScore[j] -= giftDia[i][j];
			}
		}

		int[] nextGift = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j)
					continue;

				if (giftDia[i][j] == giftDia[j][i]) {
					if (giftScore[i] > giftScore[j]) {
						nextGift[i]++;
					} else if (giftScore[j] > giftScore[i]) {
						nextGift[j]++;
					}

				} else if (giftDia[i][j] > giftDia[j][i]) {
					nextGift[i]++;
				} else {
					nextGift[j]++;
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			answer = Math.max(answer, nextGift[i]);
		}

		return answer;
	}

}
