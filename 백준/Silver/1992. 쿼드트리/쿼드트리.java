import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[][] video;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		video = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				video[i][j] = str.charAt(j) - '0';
			}
		}
		
		encodVideo(0, 0, N);
		
		System.out.println(sb);

		br.close();
	}
	
	static void encodVideo(int x, int y, int size) {

		int color = video[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (video[i][j] != color) {
					sb.append("(");
					encodVideo(x, y, size / 2);
					encodVideo(x, y + size / 2, size / 2);
					encodVideo(x + size / 2, y, size / 2);
					encodVideo(x + size / 2, y + size / 2, size / 2);
					sb.append(")");
					return;
				}
			}
		}

		if (color == 1)
			sb.append("1");
		else
			sb.append("0");

	}

}
