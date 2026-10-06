import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * B구획의 빌딩 최고 높이 구하기
 */ 
class Main {

	private static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			char[][] map = new char[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}

			int[] di = { -1, 1, 0, 0 };
			int[] dj = { 0, 0, -1, 1 };
			
			int[] gi = {-1, -1, -1, 0, 1, 1, 1, 0};
			int[] gj = {-1, 0, 1, 1, 1, 0, -1, -1};

			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (map[i][j] == 'G')
						continue;
					
					int h = 1;
					
					for(int g = 0; g < 8; g++) {
						int ni = i + gi[g];
						int nj = j + gj[g];
						
						if(ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] == 'G') {
							h = 2;
							break;
						}
					}
					
					if(h == 1) {
						for (int d = 0; d < 4; d++) {
							int ni = i + di[d];
							int nj = j + dj[d];
							while (ni >= 0 && ni < N && nj >= 0 && nj < N) {
								if(map[ni][nj] == 'B') {
									h++;
								}
								ni += di[d];
								nj += dj[d];
							}
						}
					}
					ans = Math.max(ans, h);
				}
			}

			System.out.println("#" + t + " " + ans);
		}

	}
}
