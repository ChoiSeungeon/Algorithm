import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, numBlue, numWhite;
	static int[][] colorPaper;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		colorPaper = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				colorPaper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divPaper(0, 0, N);

		System.out.println(numWhite);
		System.out.println(numBlue);

		br.close();
	}

	static void divPaper(int x, int y, int size) {

		int color = colorPaper[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (colorPaper[i][j] != color) {
					divPaper(x, y, size / 2);
					divPaper(x, y + size / 2, size / 2);
					divPaper(x + size / 2, y, size / 2);
					divPaper(x + size / 2, y + size / 2, size / 2);
					return;
				}
			}
		}

		if (color == 1)
			numBlue++;
		else
			numWhite++;

	}

}
