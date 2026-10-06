import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            char[][] map = new char[N][N];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            long ans = 0;

            // d = (b - a + N) % N
            for (int d = 0; d < N; d++) {
                boolean symmetric = true;

                outer:
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        int col1 = (j + d) % N;
                        int col2 = (i + d) % N;

                        if (map[i][col1] != map[j][col2]) {
                            symmetric = false;
                            break outer;
                        }
                    }
                }

                if (symmetric) {
                    ans += N;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

}
