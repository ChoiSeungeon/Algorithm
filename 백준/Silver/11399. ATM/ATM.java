import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        List<Integer> nums = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(nums);

        int t = 0, ans = 0;
        for(Integer num : nums){
            t += num;
            ans += t;
        }

        System.out.println(ans);
    }
}
