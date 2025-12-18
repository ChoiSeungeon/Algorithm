import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] memo = new int[11];
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());

            if(memo[N] != 0) {
                System.out.println(memo[N]);
                continue;
            }

            int idx = N - 1;
            while(memo[idx] == 0) {
                idx--;
            }

            while(idx < N){
                idx++;
                memo[idx] = memo[idx-3] + memo[idx-2] + memo[idx-1];
            }

            System.out.println(memo[N]);
        }
    }
}
