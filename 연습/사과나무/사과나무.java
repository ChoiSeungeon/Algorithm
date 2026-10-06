import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] di = {-1, 1, 0, 0};
		int[] dj = {0, 0, -1, 1};
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				int nut = map[i][j];
				
				for(int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					
					if(ni < 0 || ni >= N || nj < 0 || nj >= N)
						continue;
					
					nut += map[ni][nj];
				}
				
				ans = Math.max(ans, nut);
			}
		}

		System.out.println(ans);
	}

}
