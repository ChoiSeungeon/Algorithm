import java.util.Scanner;

public class Main {

	static int N, r, c;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();

		makeZ(0, 0, 0, (int) Math.pow(2, N));


	}

	static void makeZ(int x, int y, int cnt, int size) {

		if (x > r || y > c || x + size <= r || y + size <= c)
			return;

		if (size <= 2) {
			int n = 0;
			for (int i = x; i < x + 2; i++) {
				for (int j = y; j < y + 2; j++) {
					if (i == r && j == c)
						System.out.println(cnt + n);
					n++;
				}
			}
			return;
		}

		makeZ(x, y, cnt + (size / 2 * size / 2 * 0), size / 2);
		makeZ(x, y + size / 2, cnt + (size / 2 * size / 2 * 1), size / 2);
		makeZ(x + size / 2, y, cnt + (size / 2 * size / 2 * 2), size / 2);
		makeZ(x + size / 2, y + size / 2, cnt + (size / 2 * size / 2 * 3), size / 2);

	}

}
