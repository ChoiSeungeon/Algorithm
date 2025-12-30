import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Long[] tree = new Long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            tree[i] = Long.parseLong(st.nextToken());
        }

        long left = 0, right = 1000000000;
        long mid = (left + right) / 2;

        while(left <= right){
            long sum = 0;
            for(long t : tree){
                if(t-mid < 0)
                    continue;
                sum += t-mid;
            }

            if(sum == M) {
                break;
            } else if (sum > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

            mid = (left + right) / 2;
        }

        System.out.println(mid);
    }
}
